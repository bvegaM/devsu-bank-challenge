package com.msa.client.service.infrastructure.adapters.in.mapper;

import com.msa.client.service.domain.models.Client;
import com.msa.client.service.server.models.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {

    Client toClient(ClientDto clientDto);
    ClientDto toClientDto(Client client);
}
