package com.msa.product.service.infrastructure.adapters.out.account;

import com.msa.product.service.application.ports.GenericOPort;
import com.msa.product.service.domain.exceptions.NotFoundServiceException;
import com.msa.product.service.domain.models.Account;
import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import com.msa.product.service.infrastructure.adapters.out.account.mapper.AccountEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements GenericOPort<Account, Integer> {

    private final AccountRepository repository;
    private final AccountEntityMapper mapper;

    @Override
    public Account save(Account body) {
        AccountEntity account = mapper.toAccountEntity(body);
        return mapper.toAccount(repository.save(account));
    }

    @Override
    public Account getById(Integer id) {
        Optional<AccountEntity> client = repository.findActiveAccountById(id);
        return client.map(mapper::toAccount)
                .orElseThrow(() -> new NotFoundServiceException("Account not found"));
    }

    @Override
    public Account updateById(Account newBody) {
        return this.save(newBody);
    }

    @Override
    public void deleteById(Account body) {
        this.repository.save(mapper.toAccountEntity(body));
    }
}
