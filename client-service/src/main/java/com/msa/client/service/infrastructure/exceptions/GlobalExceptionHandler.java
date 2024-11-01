package com.msa.client.service.infrastructure.exceptions;

import com.msa.client.service.domain.exceptions.ClientServiceException;
import com.msa.client.service.domain.exceptions.SQLExceptionEnum;
import com.msa.client.service.infrastructure.utils.Util;
import com.msa.client.service.server.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            SQLException.class
    })
    public ResponseEntity<Error> sqlException(SQLException exception){
        SQLExceptionEnum sqlExceptionEnum = SQLExceptionEnum.fromCode(Integer.valueOf(exception.getSQLState()));

        Error error = new Error();
        error.setTitle("SQL Error");
        error.setDetail(sqlExceptionEnum.getDescription());
        error.setErrors(List.of());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Error> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        var violations = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(Util::generateErrorDetail)
                .toList();

        Error error = new Error();
        error.setTitle("Violation Error");
        error.setDetail("The following conditions in the parameters were violated");
        error.setErrors(violations);
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({
            ClientServiceException.class
    })
    public ResponseEntity<Error> clientServiceException(ClientServiceException exception){
        Error error = new Error();
        error.setTitle("Client Service Error");
        error.setDetail(exception.getMessage());
        error.setErrors(List.of());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(error);
    }
}
