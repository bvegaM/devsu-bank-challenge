package com.msa.product.service.infrastructure.adapters.out.movement.entity;

import com.msa.product.service.domain.models.enums.MovementTypeEnum;
import com.msa.product.service.infrastructure.adapters.out.account.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_MOVEMENTS")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOV_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "MOV_DATE", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOV_TYPE", nullable = false)
    private MovementTypeEnum movementType;

    @Column(name = "MOV_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "MOV_BALANCE", nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOV_ACC_ID", nullable = false)
    private AccountEntity account;

}
