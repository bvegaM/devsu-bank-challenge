package com.msa.client.service.infrastructure.adapters.out.client;

import com.msa.client.service.application.ports.ClientOPort;
import com.msa.client.service.domain.models.Client;
import com.msa.client.service.infrastructure.adapters.out.client.entity.ClientEntity;
import com.msa.client.service.infrastructure.adapters.out.client.mapper.ClientEntityMapper;
import com.msa.client.service.infrastructure.adapters.out.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientAdapter implements ClientOPort {

    private final ClientRepository clientRepository;
    private final ClientEntityMapper mapper;
    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = mapper.toClientEntity(client);
        return mapper.toClient(clientRepository.save(clientEntity));
    }
}