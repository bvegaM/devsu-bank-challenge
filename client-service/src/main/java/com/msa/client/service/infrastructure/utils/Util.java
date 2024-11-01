package com.msa.client.service.infrastructure.utils;

import com.msa.client.service.domain.exceptions.ValidationExceptionEnum;
import com.msa.client.service.server.models.ErrorDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Util {

    private Util() {
    }

    public static ErrorDetail generateErrorDetail(ObjectError error) {
        ErrorDetail errorDetail = new ErrorDetail();
        String attributeName = "unknown";

        if (error instanceof FieldError fieldError) {
            attributeName = fieldError.getField();
        }

        String message = formatErrorMessage(attributeName, error.getDefaultMessage());
        errorDetail.message(message);

        return errorDetail;
    }

    private static String formatErrorMessage(String attributeName, String defaultMessage) {
        if (Boolean.TRUE.equals(ValidationExceptionEnum.containsPattern(defaultMessage))) {
            return attributeName + ":" + ValidationExceptionEnum.fromPattern(defaultMessage).getDescription();
        } else {
            return attributeName + ":" + defaultMessage;
        }
    }

}
