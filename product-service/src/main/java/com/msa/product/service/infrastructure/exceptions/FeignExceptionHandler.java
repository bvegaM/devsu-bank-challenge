package com.msa.product.service.infrastructure.exceptions;

import com.msa.product.service.server.models.Error;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler({
            FeignException.NotFound.class
    })
    public ResponseEntity<Error> feignException(FeignException exception){
        return ResponseEntity.notFound().build();
    }
}
