package com.msa.product.service.domain.exceptions;

public class TransactionServiceException extends RuntimeException {

    public TransactionServiceException(String message) {
        super(message);
    }
}
