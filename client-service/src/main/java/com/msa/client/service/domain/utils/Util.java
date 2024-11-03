package com.msa.client.service.domain.utils;

import com.msa.client.service.domain.exceptions.ClientServiceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {

    private Util() {
    }

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean validateDateRange(String fromDate, String toDate) {
        try {
            LocalDate from = LocalDate.parse(fromDate, DATE_FORMATTER);
            LocalDate to = LocalDate.parse(toDate, DATE_FORMATTER);
            if (from.isAfter(to)) {
                throw new ClientServiceException("Error: fromDate cannot be after toDate");
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new ClientServiceException("Error: Incorrect date format. The format must be yyyy-MM-dd");
        }
    }
}
