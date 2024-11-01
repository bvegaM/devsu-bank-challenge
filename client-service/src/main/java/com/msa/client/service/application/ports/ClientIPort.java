package com.msa.client.service.application.ports;

import com.msa.client.service.domain.models.Client;

public interface ClientIPort {

    Client save(Client client);

    Client getById(Integer id);

    void deleteById(Integer id);
}
