package com.msa.product.service.application.ports;

import com.msa.product.service.domain.models.*;

import java.util.List;

public interface MovementOPort {

    Movement save(Movement movement);

    Movement getById(Integer id);

    List<Report> generateReport(ReportRequest request);

    void sendReport(ReportRequest request, List<Report> reports);
}
