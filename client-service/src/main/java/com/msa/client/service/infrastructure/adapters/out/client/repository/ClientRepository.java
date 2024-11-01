package com.msa.client.service.infrastructure.adapters.out.client.repository;

import com.msa.client.service.infrastructure.adapters.out.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
