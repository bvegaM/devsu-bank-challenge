package com.msa.product.service.domain.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.msa.product.service.domain.utils.Constant.DATE_FORMATTER;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportRequest {

    private Integer clientId;

    private String clientName;

    private String fromDate;

    private String toDate;

    public static LocalDateTime toStartOfDay(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        return date.atStartOfDay();
    }

    public static LocalDateTime toEndOfDay(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        return date.atTime(23, 59, 0);
    }
}
