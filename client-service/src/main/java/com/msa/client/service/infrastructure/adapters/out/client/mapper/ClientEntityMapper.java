package com.msa.client.service.infrastructure.adapters.out.client.mapper;

import com.msa.client.service.domain.models.Client;
import com.msa.client.service.infrastructure.adapters.out.client.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    ClientEntity toClientEntity(Client client);
    Client toClient(ClientEntity client);
}
