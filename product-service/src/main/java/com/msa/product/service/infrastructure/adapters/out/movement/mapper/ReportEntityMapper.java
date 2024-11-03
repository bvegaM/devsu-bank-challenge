package com.msa.product.service.infrastructure.adapters.out.movement.mapper;

import com.msa.product.service.domain.models.Report;
import com.msa.product.service.infrastructure.adapters.out.account.mapper.AccountEntityMapper;
import com.msa.product.service.infrastructure.adapters.out.movement.entity.ReportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovementEntityMapper.class, AccountEntityMapper.class})
public interface ReportEntityMapper {

    List<Report> toReportEntities(List<ReportEntity> reportEntities);
}
