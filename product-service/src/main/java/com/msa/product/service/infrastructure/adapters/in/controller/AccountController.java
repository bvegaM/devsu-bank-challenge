package com.msa.product.service.infrastructure.adapters.in.controller;

import com.msa.product.service.application.ports.GenericIPort;
import com.msa.product.service.domain.models.Account;
import com.msa.product.service.infrastructure.adapters.in.mapper.AccountDtoMapper;
import com.msa.product.service.server.AccountsApi;
import com.msa.product.service.server.models.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {

    private final GenericIPort<Account, Integer> accountIPort;
    private final AccountDtoMapper mapper;

    @Override
    public ResponseEntity<AccountDto> accountsPost(AccountDto accountDto) {
        Account account = accountIPort.save(mapper.toAccount(accountDto));
        return new ResponseEntity<>(mapper.toAccountDto(account), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDto> accountsAccountIdGet(Integer accountId) {
        Account account = accountIPort.getById(accountId);
        return new ResponseEntity<>(mapper.toAccountDto(account), HttpStatus.OK);
    }
}
