package com.msa.product.service.infrastructure.adapters.out.movement.entity;

import com.msa.product.service.domain.models.enums.AccountTypeEnum;
import com.msa.product.service.domain.models.enums.MovementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportEntity {

    private String clientName;
    private String accountNumber;
    private AccountTypeEnum accountType;
    private BigDecimal initialBalance;
    private LocalDateTime date;
    private MovementTypeEnum movementType;
    private BigDecimal value;
    private BigDecimal balance;
}
