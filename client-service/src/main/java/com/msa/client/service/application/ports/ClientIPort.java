package com.msa.client.service.application.ports;

import com.msa.client.service.domain.models.Client;

public interface ClientIPort {

    Client save(Client client);

    Client getById(Integer id);

    void deleteById(Integer id);

    Client updateById(Integer id, Client client);

    void generateReport(Integer id, String fromDate, String toDate);
}
