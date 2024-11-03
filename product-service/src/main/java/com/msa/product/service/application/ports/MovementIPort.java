package com.msa.product.service.application.ports;

import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.domain.models.ReportRequest;


public interface MovementIPort {

    Movement save(Movement movement);

    Movement getById(Integer id);

    void generateReport(ReportRequest request);
}
