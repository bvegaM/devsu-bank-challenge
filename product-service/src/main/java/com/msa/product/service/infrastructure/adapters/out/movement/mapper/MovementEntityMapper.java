package com.msa.product.service.infrastructure.adapters.out.movement.mapper;

import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {

    @Mapping(target = "account.movements", ignore = true)
    Movement toMovement(MovementEntity movementEntity);
    MovementEntity toMovementEntity(Movement movement);
}
