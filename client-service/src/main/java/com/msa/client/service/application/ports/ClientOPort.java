package com.msa.client.service.application.ports;

import com.msa.client.service.domain.models.Client;

public interface ClientOPort {

    Client save(Client client);

    Client getById(Integer id);

    void deleteById(Client client);

    Client updateById(Client client);
}
