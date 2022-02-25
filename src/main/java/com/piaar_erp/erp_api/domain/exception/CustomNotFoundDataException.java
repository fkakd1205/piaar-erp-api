package com.piaar_erp.erp_api.domain.exception;

/**
 * not found data exception
 * http status 404
 */
public class CustomNotFoundDataException extends RuntimeException {
    public CustomNotFoundDataException(String message) {
        super(message);
    }
    public CustomNotFoundDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
