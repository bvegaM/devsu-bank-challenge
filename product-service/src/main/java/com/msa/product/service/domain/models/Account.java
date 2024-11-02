package com.msa.product.service.domain.models;

import com.msa.product.service.domain.exceptions.AccountServiceException;
import com.msa.product.service.domain.models.enums.AccountTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private Integer id;

    private String accountNumber;

    private AccountTypeEnum accountType;

    private BigDecimal initialBalance;

    private Integer status;

    private Integer clientId;

    private List<Movement> movements;

    public void deactivateAccount(){
        this.setStatus(0);
    }

    public void updateAccount(Account newAccount) {
        if (newAccount == null) {
            throw new AccountServiceException("No new account provided for update.");
        }

        updateIfDifferent(this::getAccountNumber, this::setAccountNumber, newAccount.getAccountNumber());
        updateIfDifferent(this::getAccountType, this::setAccountType, newAccount.getAccountType());
        updateIfDifferent(this::getInitialBalance, this::setInitialBalance, newAccount.getInitialBalance());
        updateIfDifferent(this::getStatus, this::setStatus, newAccount.getStatus());
        updateIfDifferent(this::getClientId, this::setClientId, newAccount.getClientId());
    }

    private <T> void updateIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        T currentValue = getter.get();
        if ((currentValue == null && newValue != null) ||
                (currentValue != null && !currentValue.equals(newValue))) {
            setter.accept(newValue);
        }
    }
}
