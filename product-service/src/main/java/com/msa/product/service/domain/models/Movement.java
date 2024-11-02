package com.msa.product.service.domain.models;

import com.msa.product.service.domain.models.enums.MovementTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    private Integer id;

    private LocalDateTime date;

    private MovementTypeEnum movementType;

    private BigDecimal value;

    private BigDecimal balance;

    private Account account;
}
