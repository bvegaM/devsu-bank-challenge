package com.msa.product.service.infrastructure.adapters.out.kafka.mapper;

import com.msa.product.service.domain.models.ReportRequest;
import com.msa.product.service.infrastructure.adapters.out.kafka.entity.ReportRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportRequestMapper {

    ReportRequest toReportRequest(ReportRequestEntity request);
}
