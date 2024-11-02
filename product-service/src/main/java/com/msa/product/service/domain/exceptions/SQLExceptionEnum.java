package com.msa.product.service.domain.exceptions;

import lombok.Getter;

@Getter
public enum SQLExceptionEnum {

    ERROR_CONSTRAINT(23505,"Unique index or primary key violation");

    private final Integer code;

    private final String description;

    SQLExceptionEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    public static SQLExceptionEnum fromCode(Integer code) {
        for (SQLExceptionEnum error : SQLExceptionEnum.values()) {
            if (error.getCode().equals(code)) {
                return error;
            }
        }
        throw new IllegalArgumentException("No enum found for code: " + code);
    }
}
