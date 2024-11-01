package com.msa.client.service.infrastructure.adapters.out.client.repository;

import com.msa.client.service.infrastructure.adapters.out.client.entity.ClientEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Query("SELECT c FROM ClientEntity c WHERE c.id = :id AND c.status = 1")
    Optional<ClientEntity> findActiveClientById(@Param("id") Integer id);
}
