package com.msa.product.service.domain.models.strategy.movement;

import com.msa.product.service.domain.exceptions.TransactionServiceException;
import com.msa.product.service.domain.models.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositMovementStrategy implements MovementStrategy{

    @Override
    public void validateTransaction(Movement movement) {
        MovementStrategy.super.validateTransaction(movement);
        if(movement.getValue().compareTo(BigDecimal.ZERO) < 0){
            throw new TransactionServiceException("Deposit must have positive values");
        }
    }

    @Override
    public Movement processMovement(Movement movement) {
        executeMovement(movement);
        this.validateTransaction(movement);
        movement.setDate(LocalDateTime.now());
        return movement;
    }

    @Override
    public void executeMovement(Movement movement) {
        BigDecimal newBalance = movement.getAccount().getInitialBalance().add(
                movement.getValue().abs()
        );
        movement.setBalance(newBalance);
    }
}
