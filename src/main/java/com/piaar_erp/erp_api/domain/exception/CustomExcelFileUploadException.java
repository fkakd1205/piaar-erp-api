package com.piaar_erp.erp_api.domain.exception;

public class CustomExcelFileUploadException extends RuntimeException {
    public CustomExcelFileUploadException(String message) {
        super(message);
    }

    public CustomExcelFileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
