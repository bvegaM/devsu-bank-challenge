package com.msa.product.service.infrastructure.adapters.out.movement.repository;

import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
}
