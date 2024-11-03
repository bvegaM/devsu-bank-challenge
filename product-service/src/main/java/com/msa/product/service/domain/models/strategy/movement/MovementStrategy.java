package com.msa.product.service.domain.models.strategy.movement;

import com.msa.product.service.domain.exceptions.TransactionServiceException;
import com.msa.product.service.domain.models.Movement;

import java.math.BigDecimal;

public interface MovementStrategy {
    Movement processMovement(Movement movement);

    void executeMovement(Movement movement);
    default void validateTransaction(Movement movement){
        if (movement.getValue().compareTo(BigDecimal.ZERO) == 0){
            throw new TransactionServiceException("No 0-value transactions can be carried out");
        }
    }
}
