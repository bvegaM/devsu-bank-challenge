package com.msa.product.service.infrastructure.adapters.out.account.repository;

import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query("SELECT a FROM AccountEntity a WHERE a.id = :id AND a.status = 1")
    Optional<AccountEntity> findActiveAccountById(@Param("id") Integer id);
}
