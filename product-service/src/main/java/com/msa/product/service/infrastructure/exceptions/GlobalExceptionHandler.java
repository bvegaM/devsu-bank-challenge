package com.msa.product.service.infrastructure.exceptions;

import com.msa.product.service.domain.exceptions.SQLExceptionEnum;
import com.msa.product.service.infrastructure.utils.Util;
import com.msa.product.service.server.models.Error;
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

        Error error = Util.createError(
                "SQL Error", sqlExceptionEnum.getDescription(), List.of(),
                HttpStatus.BAD_REQUEST);

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

        Error error = Util.createError("Violation Error",
                "The following conditions in the parameters were violated", violations,
                HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(error);
    }
}
