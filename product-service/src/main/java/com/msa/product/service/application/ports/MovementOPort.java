package com.msa.product.service.application.ports;

import com.msa.product.service.domain.models.Movement;

public interface MovementOPort {

    Movement save(Movement movement);
}
