package com.msa.client.service.infrastructure.utils;

import com.msa.client.service.domain.exceptions.ValidationExceptionEnum;
import com.msa.client.service.server.models.Error;
import com.msa.client.service.server.models.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

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

    public static ErrorDetail generateErrorDetail(String message) {
        ErrorDetail errorDetail = new ErrorDetail();
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

    public static Error createError(String title, String detail, List<ErrorDetail> errorDetails, HttpStatus status) {
        Error error = new Error();
        error.setTitle(title);
        error.setDetail(detail);
        error.setErrors(errorDetails);
        error.setStatus(status.value());
        return error;
    }

}
