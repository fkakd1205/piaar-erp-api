package com.piaar_erp.erp_api.domain.exception;
/**
 * user access denied exception
 * http status 403
 */
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
