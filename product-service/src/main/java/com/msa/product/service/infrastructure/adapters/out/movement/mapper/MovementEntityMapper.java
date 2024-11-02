package com.msa.product.service.infrastructure.adapters.out.movement.mapper;

import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {

    Movement toMovement(MovementEntity movementEntity);
    MovementEntity toMovementEntity(Movement movement);
}
