package com.msa.product.service.domain.models.strategy;

import com.msa.product.service.domain.exceptions.AccountServiceException;
import com.msa.product.service.domain.models.enums.MovementTypeEnum;
import com.msa.product.service.domain.models.strategy.movement.DepositMovementStrategy;
import com.msa.product.service.domain.models.strategy.movement.MovementStrategy;
import com.msa.product.service.domain.models.strategy.movement.WithdrawalMovementStrategy;

public class MovementStrategyProcessor {

    private MovementStrategyProcessor() {
    }

    public static MovementStrategy processMovement(MovementTypeEnum movementType){
        if(MovementTypeEnum.DEPOSIT.equals(movementType)){
            return new DepositMovementStrategy();
        }
        if(MovementTypeEnum.WITHDRAWAL.equals(movementType)){
            return new WithdrawalMovementStrategy();
        }
        throw new AccountServiceException("cant found movement type");
    }
}
