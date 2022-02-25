package com.piaar_erp.erp_api.domain.exception;
/**
 * user access denied exception
 * http status 403
 */
public class CustomAccessDeniedException extends RuntimeException{
    public CustomAccessDeniedException(String message) {
        super(message);
    }
    public CustomAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
