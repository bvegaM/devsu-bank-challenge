package com.msa.product.service.infrastructure.adapters.out.client.repository;

import com.msa.product.service.infrastructure.adapters.out.client.entity.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url = "http://localhost:8081/clients")
public interface ClientRepository {

    @GetMapping(value = "/{id}")
    ResponseEntity<ClientEntity> getById(@PathVariable("id") Integer id);
}
