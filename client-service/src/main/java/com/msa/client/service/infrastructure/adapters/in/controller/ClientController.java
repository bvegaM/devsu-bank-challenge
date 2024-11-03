package com.msa.client.service.infrastructure.adapters.in.controller;

import com.msa.client.service.application.ports.ClientIPort;
import com.msa.client.service.domain.models.Client;
import com.msa.client.service.infrastructure.adapters.in.mapper.ClientDtoMapper;
import com.msa.client.service.server.ClientsApi;
import com.msa.client.service.server.models.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientIPort clientIPort;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        Client client = clientIPort
                .save(clientDtoMapper.toClient(clientDto));

        return new ResponseEntity<>(clientDtoMapper
                .toClientDto(client), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDto> getClientById(Integer id) {
        Client client = clientIPort.getById(id);
        return new ResponseEntity<>(clientDtoMapper.toClientDto(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteClient(Integer id) {
        clientIPort.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ClientDto> updateClient(Integer id, ClientDto clientDto) {
        Client client = clientIPort.updateById(id, clientDtoMapper.toClient(clientDto));
        return new ResponseEntity<>(clientDtoMapper.toClientDto(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> generateClientReport(Integer id, String fromDate, String toDate){
        clientIPort.generateReport(id, fromDate, toDate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
