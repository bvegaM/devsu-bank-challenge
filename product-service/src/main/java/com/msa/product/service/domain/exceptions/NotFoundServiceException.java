package com.msa.product.service.domain.exceptions;

public class NotFoundServiceException extends RuntimeException {

    public NotFoundServiceException(String message) {
        super(message);
    }

}
