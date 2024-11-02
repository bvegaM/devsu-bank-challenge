package com.msa.product.service.infrastructure.adapters.out.movement;

import com.msa.product.service.application.ports.MovementOPort;
import com.msa.product.service.domain.exceptions.NotFoundServiceException;
import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import com.msa.product.service.infrastructure.adapters.out.movement.mapper.MovementEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.movement.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class MovementAdapter implements MovementOPort {

    private final MovementRepository repository;
    private final MovementEntityMapper mapper;

    @Override
    public Movement save(Movement movement) {
        MovementEntity movementEntity = repository.save(mapper.toMovementEntity(movement));
        return mapper.toMovement(movementEntity);
    }

    @Override
    public Movement getById(Integer id) {
        Optional<MovementEntity> movement = repository.findById(id);
        return movement.map(mapper::toMovement)
                .orElseThrow(() -> new NotFoundServiceException("movement not found"));
    }
}
