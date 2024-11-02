package com.msa.product.service.infrastructure.adapters.out.account;

import com.msa.product.service.application.ports.GenericOPort;
import com.msa.product.service.domain.models.Account;
import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import com.msa.product.service.infrastructure.adapters.out.account.mapper.AccountEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return null;
    }

    @Override
    public Account updateById(Account newBody) {
        return null;
    }

    @Override
    public void deleteById(Account body) {
        //TODO
    }
}
