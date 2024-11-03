package com.msa.product.service.domain.utils;

import java.time.format.DateTimeFormatter;

public class Constant {

    private Constant() {
    }

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
