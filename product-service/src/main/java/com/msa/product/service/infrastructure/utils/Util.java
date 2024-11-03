package com.msa.product.service.infrastructure.utils;

import com.msa.product.service.domain.exceptions.ValidationExceptionEnum;
import com.msa.product.service.server.models.Error;
import com.msa.product.service.server.models.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
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

    public static void saveJsonToFile(String jsonContent, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                log.info("Directory created at: " + parentDir.toString());
            }

            Files.write(path, jsonContent.getBytes());
            log.info("Report generated successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Problem generating JSON report file", e);
        }
    }
}
