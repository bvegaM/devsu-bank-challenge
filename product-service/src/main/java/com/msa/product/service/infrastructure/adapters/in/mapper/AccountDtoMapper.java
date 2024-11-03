package com.msa.product.service.infrastructure.adapters.in.mapper;

import com.msa.product.service.domain.models.Account;
import com.msa.product.service.server.models.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    @Mapping(target = "movements", ignore = true)
    Account toAccount(AccountDto accountDto);
    AccountDto toAccountDto(Account account);
}
