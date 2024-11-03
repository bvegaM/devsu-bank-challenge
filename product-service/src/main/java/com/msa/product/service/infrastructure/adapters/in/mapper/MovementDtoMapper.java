package com.msa.product.service.infrastructure.adapters.in.mapper;

import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.server.models.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementDtoMapper {

    @Mapping(target = "account.id", source = "accountId")
    Movement toMovement(MovementDto movementDto);
    @Mapping(target = "accountId", source = "account.id")
    MovementDto toMovementDto(Movement movement);
}
