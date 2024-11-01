package com.msa.client.service.application.ports;

import com.msa.client.service.domain.models.Client;

public interface ClientOPort {

    Client save(Client client);
}
