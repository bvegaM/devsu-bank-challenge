package com.msa.product.service.infrastructure.adapters.out.account.mapper;

import com.msa.product.service.domain.models.Account;
import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

    @Mapping(target = "movements", ignore = true)
    AccountEntity toAccountEntity(Account account);

    @Mapping(target = "movements", ignore = true)
    Account toAccount(AccountEntity accountEntity);
}
