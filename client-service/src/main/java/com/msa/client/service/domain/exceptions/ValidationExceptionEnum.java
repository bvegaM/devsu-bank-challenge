package com.msa.client.service.domain.exceptions;

import lombok.Getter;

@Getter
public enum ValidationExceptionEnum {

    NUMBER_VALIDATION("must match \"^[0-9]+$\"","This field only must be contain numbers"),
    TEXT_VALIDATION("must match \"^[a-zA-Z\\s]+$\"","This field only must be contain letters");

    private String pattern;
    private String description;

    ValidationExceptionEnum(String pattern, String description) {
        this.pattern = pattern;
        this.description = description;
    }

    public static Boolean containsPattern(String pattern){
        for (ValidationExceptionEnum error : ValidationExceptionEnum.values()) {
            if (error.getPattern().equals(pattern)) {
                return true;
            }
        }
        return false;
    }

    public static ValidationExceptionEnum fromPattern(String pattern) {
        for (ValidationExceptionEnum error : ValidationExceptionEnum.values()) {
            if (error.getPattern().equals(pattern)) {
                return error;
            }
        }
        throw new IllegalArgumentException("No enum found for pattern: " + pattern);
    }
}
