package com.msa.client.service.infrastructure.adapters.out.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.client.service.application.ports.ClientOPort;
import com.msa.client.service.domain.exceptions.ClientServiceException;
import com.msa.client.service.domain.exceptions.NotFoundServiceException;
import com.msa.client.service.domain.models.Client;
import com.msa.client.service.domain.models.Report;
import com.msa.client.service.infrastructure.adapters.out.client.entity.ClientEntity;
import com.msa.client.service.infrastructure.adapters.out.client.mapper.ClientEntityMapper;
import com.msa.client.service.infrastructure.adapters.out.client.repository.ClientRepository;
import com.msa.client.service.infrastructure.adapters.out.kafka.ProductProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientAdapter implements ClientOPort {

    private final ClientRepository clientRepository;
    private final ClientEntityMapper mapper;
    private final ProductProducer productProducer;
    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = mapper.toClientEntity(client);
        return mapper.toClient(clientRepository.save(clientEntity));
    }

    @Override
    public Client getById(Integer id) {
        Optional<ClientEntity> client = clientRepository.findActiveClientById(id);
        return client.map(mapper::toClient)
                .orElseThrow(() -> new NotFoundServiceException("Cliente no encontrado"));
    }

    @Override
    public void deleteById(Client client) {
        clientRepository.save(mapper.toClientEntity(client));
    }

    @Override
    public Client updateById(Client client) {
        return this.save(client);
    }

    @Override
    public void generateReport(Client client, String fromDate, String toDate){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Report report = Report.builder().clientId(client.getId())
                    .clientName(client.getName()).fromDate(fromDate).toDate(toDate).build();
            productProducer.sendMessage(objectMapper.writeValueAsString(report));
        }catch (JsonProcessingException e){
            throw new ClientServiceException("Error transform data with Object Mapper");
        }

    }
}
