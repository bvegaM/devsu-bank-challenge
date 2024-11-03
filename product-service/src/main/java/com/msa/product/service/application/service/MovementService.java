package com.msa.product.service.application.service;

import com.msa.product.service.application.ports.GenericIPort;
import com.msa.product.service.application.ports.MovementIPort;
import com.msa.product.service.application.ports.MovementOPort;
import com.msa.product.service.domain.models.*;
import com.msa.product.service.domain.models.strategy.MovementStrategyProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementService implements MovementIPort {

    private final MovementOPort movementOPort;
    private final GenericIPort<Account, Integer> accountIPort;

    @Override
    @Transactional
    public Movement save(Movement movement) {
        Account account = accountIPort.getById(movement.getAccount().getId());
        movement.setAccount(account);

        movement = MovementStrategyProcessor.processMovement(movement.getMovementType())
                .processMovement(movement);
        movement.getAccount().setInitialBalance(movement.getBalance());
        Movement movementToSave = movementOPort.save(movement);

        accountIPort.updateById(movementToSave.getAccount().getId(),movementToSave.getAccount());

        return movementToSave;
    }

    @Override
    public Movement getById(Integer id) {
        return movementOPort.getById(id);
    }

    @Override
    public void generateReport(ReportRequest request) {
        List<Report> reports = movementOPort.generateReport(request);
        movementOPort.sendReport(request, reports);
    }
}
