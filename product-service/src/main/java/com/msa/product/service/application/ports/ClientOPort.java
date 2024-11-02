package com.msa.product.service.application.ports;

import com.msa.product.service.domain.models.Client;

public interface ClientOPort {

    Client getById(Integer id);
}
