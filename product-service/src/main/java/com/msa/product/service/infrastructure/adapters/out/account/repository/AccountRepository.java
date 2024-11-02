package com.msa.product.service.infrastructure.adapters.out.account.repository;

import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
