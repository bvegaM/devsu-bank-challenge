package com.msa.client.service.application.service;

import com.msa.client.service.application.ports.ClientIPort;
import com.msa.client.service.application.ports.ClientOPort;
import com.msa.client.service.domain.models.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientIPort {

    private final ClientOPort clientOPort;

    @Override
    public Client save(Client client) {
        client.encryptPassword();
        return clientOPort.save(client);
    }

    @Override
    public Client getById(Integer id) {
        return clientOPort.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Client client = this.getById(id);
        client.deactivateUser();

        clientOPort.deleteById(client);
    }

    @Override
    public Client updateById(Integer id, Client client) {
        Client clientToUpdate = this.getById(id);
        clientToUpdate.updateClient(client);
        return clientOPort.updateById(clientToUpdate);
    }
}
