package com.msa.client.service.infrastructure.exceptions;

import com.msa.client.service.domain.exceptions.ClientServiceException;
import com.msa.client.service.domain.exceptions.NotFoundServiceException;
import com.msa.client.service.domain.exceptions.SQLExceptionEnum;
import com.msa.client.service.infrastructure.utils.Util;
import com.msa.client.service.server.models.Error;
import com.msa.client.service.server.models.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler({
            ClientServiceException.class
    })
    public ResponseEntity<Error> clientServiceException(ClientServiceException exception){
        Error error = Util.createError("Client Service Error", exception.getMessage(), List.of(),
                HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({
            NotFoundServiceException.class
    })
    public ResponseEntity<Error> notFoundServiceException(NotFoundServiceException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String parameterName = exception.getName();
        Class<?> requiredType = exception.getRequiredType();
        Object providedValue = exception.getValue();

        String expectedTypeName = (requiredType != null) ? requiredType.getSimpleName() : "unknown type";
        String message = String.format("Parameter '%s' expects a value of type '%s', but '%s' was provided",
                parameterName, expectedTypeName, providedValue);

        ErrorDetail errorDetail = Util.generateErrorDetail(message);
        Error error = Util.createError("Type Mismatch Error", "There was a type mismatch in the request parameters",
                List.of(errorDetail), HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(error);
    }


}
