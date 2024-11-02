package com.msa.product.service.infrastructure.adapters.in.controller;

import com.msa.product.service.application.ports.MovementIPort;
import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.infrastructure.adapters.in.mapper.MovementDtoMapper;
import com.msa.product.service.server.MovementsApi;
import com.msa.product.service.server.models.MovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovementController implements MovementsApi {

    private final MovementIPort movementIPort;
    private final MovementDtoMapper movementDtoMapper;

    @Override
    public ResponseEntity<MovementDto> movementsPost(MovementDto movementDto) {
        Movement movement = movementIPort.save(movementDtoMapper.toMovement(movementDto));
        return new ResponseEntity<>(movementDtoMapper.toMovementDto(movement), HttpStatus.CREATED);
    }
}
