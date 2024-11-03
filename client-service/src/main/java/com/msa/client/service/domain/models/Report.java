package com.msa.client.service.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    private Integer clientId;

    private String clientName;

    private String fromDate;

    private String toDate;
}
