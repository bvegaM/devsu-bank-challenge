package com.msa.product.service.infrastructure.adapters.out.movement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.product.service.application.ports.MovementOPort;
import com.msa.product.service.domain.exceptions.NotFoundServiceException;
import com.msa.product.service.domain.models.Movement;
import com.msa.product.service.domain.models.Report;
import com.msa.product.service.domain.models.ReportRequest;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.MovementEntity;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.ReportEntity;
import com.msa.product.service.infrastructure.adapters.out.movement.mapper.MovementEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.movement.mapper.ReportEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.movement.repository.MovementRepository;
import com.msa.product.service.infrastructure.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovementAdapter implements MovementOPort {

    private final MovementRepository repository;
    private final MovementEntityMapper mapper;
    private final ReportEntityMapper reportEntityMapper;
    private final ObjectMapper objectMapper;

    @Value("${report.path}")
    private String reportPath;

    @Override
    public Movement save(Movement movement) {
        MovementEntity movementEntity = repository.save(mapper.toMovementEntity(movement));
        return mapper.toMovement(movementEntity);
    }

    @Override
    public Movement getById(Integer id) {
        Optional<MovementEntity> movement = repository.findById(id);
        return movement.map(mapper::toMovement)
                .orElseThrow(() -> new NotFoundServiceException("movement not found"));
    }

    @Override
    public List<Report> generateReport(ReportRequest request) {

        List<ReportEntity> reportEntities = repository.findByAccountClientIdAndDateBetween(request.getClientName(),
                request.getClientId(),ReportRequest.toStartOfDay(request.getFromDate()),
                ReportRequest.toEndOfDay(request.getToDate()));

        return  reportEntityMapper.toReportEntities(reportEntities);
    }

    @Override
    public void sendReport(ReportRequest request, List<Report> reports) {
        try{

            String builderPath = new StringBuilder().append(reportPath)
                    .append("/").append(request.getClientName().replace(" ",""))
                    .append(".json").toString();

            Util.saveJsonToFile(objectMapper.writeValueAsString(reports), builderPath);
        }catch (JsonProcessingException e){
            log.error("error in generate file process");
        }

    }
}
