package com.msa.client.service.domain.exceptions;

public class ClientServiceException extends RuntimeException {

    public ClientServiceException(String message) {
        super(message);
    }

    public ClientServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
