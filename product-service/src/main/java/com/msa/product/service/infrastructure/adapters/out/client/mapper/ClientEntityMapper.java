package com.msa.product.service.infrastructure.adapters.out.client.mapper;

import com.msa.product.service.domain.models.Client;
import com.msa.product.service.infrastructure.adapters.out.client.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    Client toClient(ClientEntity clientEntity);
}
