package com.msa.product.service.application.ports;

import com.msa.product.service.domain.models.Movement;

public interface MovementIPort {

    Movement save(Movement movement);
}
