package com.msa.product.service.domain.models.strategy.movement;

import com.msa.product.service.domain.exceptions.TransactionServiceException;
import com.msa.product.service.domain.models.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawalMovementStrategy implements MovementStrategy {

    @Override
    public void validateTransaction(Movement movement) {
        MovementStrategy.super.validateTransaction(movement);
        if(movement.getValue().compareTo(BigDecimal.ZERO) > 0){
            throw new TransactionServiceException("Withdrawal must have negative values");
        }
        if(movement.getAccount().getInitialBalance().compareTo(BigDecimal.ZERO) < 0 ||
                movement.getBalance().compareTo(BigDecimal.ZERO) < 0){
            throw new TransactionServiceException("balance not available");
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
        BigDecimal newBalance = movement.getAccount().getInitialBalance().subtract(
                movement.getValue().abs()
        );
        movement.setBalance(newBalance);
    }
}
