package com.msa.product.service.domain.models;

import com.msa.product.service.domain.models.enums.AccountTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
}
