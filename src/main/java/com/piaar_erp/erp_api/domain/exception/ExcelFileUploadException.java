package com.piaar_erp.erp_api.domain.exception;

public class ExcelFileUploadException extends RuntimeException {
    public ExcelFileUploadException(String message) {
        super(message);
    }

    public ExcelFileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
