package com.msa.product.service.infrastructure.adapters.out.kafka.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportRequestEntity {

    private Integer clientId;

    private String clientName;

    private String fromDate;

    private String toDate;
}
