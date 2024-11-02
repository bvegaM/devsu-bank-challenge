package com.msa.product.service.application.service;

import com.msa.product.service.application.ports.ClientOPort;
import com.msa.product.service.application.ports.GenericIPort;
import com.msa.product.service.application.ports.GenericOPort;
import com.msa.product.service.domain.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService implements GenericIPort<Account, Integer> {

    private final GenericOPort<Account, Integer> accountOPort;
    private final ClientOPort clientOPort;

    @Override
    @Transactional
    public Account save(Account body) {
        clientOPort.getById(body.getClientId());
        return accountOPort.save(body);
    }

    @Override
    public Account getById(Integer id) {
        return accountOPort.getById(id);
    }

    @Override
    public Account updateById(Integer id, Account newBody) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Account account = this.getById(id);
        account.deactivateAccount();

        accountOPort.deleteById(account);
    }
}
