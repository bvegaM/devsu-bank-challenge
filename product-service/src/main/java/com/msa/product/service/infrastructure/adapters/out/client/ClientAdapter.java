package com.msa.product.service.infrastructure.adapters.out.client;

import com.msa.product.service.application.ports.ClientOPort;
import com.msa.product.service.domain.models.Client;
import com.msa.product.service.infrastructure.adapters.out.client.entity.ClientEntity;
import com.msa.product.service.infrastructure.adapters.out.client.mapper.ClientEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientAdapter implements ClientOPort {

    private final ClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    public Client getById(Integer id) {
        ResponseEntity<ClientEntity> client = clientRepository.getById(id);
        return clientEntityMapper.toClient(client.getBody());
    }
}
