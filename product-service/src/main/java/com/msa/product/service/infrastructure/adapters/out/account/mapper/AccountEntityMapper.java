package com.msa.product.service.infrastructure.adapters.out.account.mapper;

import com.msa.product.service.domain.models.Account;
import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import com.msa.product.service.infrastructure.adapters.out.movement.mapper.MovementEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MovementEntityMapper.class})
public interface AccountEntityMapper {

    @Mapping(target = "movements", ignore = true)
    AccountEntity toAccountEntity(Account account);


    Account toAccount(AccountEntity accountEntity);
}
