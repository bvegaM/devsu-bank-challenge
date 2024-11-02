package com.msa.product.service.infrastructure.adapters.out.account.entity;


import com.msa.product.service.domain.models.enums.AccountTypeEnum;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_ACCOUNTS")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ACC_NUMBER", unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TYPE", nullable = false)
    private AccountTypeEnum accountType;

    @Column(name = "ACC_INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "ACC_STATUS", nullable = false)
    private Integer status;

    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovementEntity> movements;
}
