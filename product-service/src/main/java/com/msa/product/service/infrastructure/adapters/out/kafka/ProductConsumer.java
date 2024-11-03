package com.msa.product.service.infrastructure.adapters.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.product.service.application.ports.MovementIPort;
import com.msa.product.service.infrastructure.adapters.out.kafka.entity.ReportRequestEntity;
import com.msa.product.service.infrastructure.adapters.out.kafka.mapper.ReportRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductConsumer {

    private final ObjectMapper objectMapper;
    private final ReportRequestMapper mapper;
    private final MovementIPort movementIPort;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String message) {
        try{
            ReportRequestEntity reportRequestEntity = objectMapper
                    .readValue(message, ReportRequestEntity.class);

            movementIPort.generateReport(mapper.toReportRequest(reportRequestEntity));
        }catch (JsonProcessingException e){
            log.error("Error to transform data in consumer");
        }
    }
}
