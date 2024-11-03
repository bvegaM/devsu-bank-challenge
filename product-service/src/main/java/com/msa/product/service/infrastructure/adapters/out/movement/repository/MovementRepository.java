package com.msa.product.service.infrastructure.adapters.out.movement.repository;

import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

    @Query("SELECT new com.msa.product.service.infrastructure.adapters.out.movement.entity.ReportEntity(" +
            ":clientName, a.accountNumber, a.accountType, a.initialBalance, m.date, m.movementType, m.value, m.balance) " +
            "FROM MovementEntity m " +
            "JOIN m.account a " +
            "WHERE a.clientId = :clientId " +
            "AND a.status = 1 " +
            "AND m.date BETWEEN :fromDate AND :toDate " +
            "ORDER BY a.id, m.date DESC")
    List<ReportEntity> findByAccountClientIdAndDateBetween(String clientName, Integer clientId, LocalDateTime fromDate,
                                                           LocalDateTime toDate);
}
