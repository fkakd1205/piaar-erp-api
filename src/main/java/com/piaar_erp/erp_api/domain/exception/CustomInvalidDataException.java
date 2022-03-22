package com.piaar_erp.erp_api.domain.exception;

/**
 * invalid data exception
 * http status 400
 */
public class CustomInvalidDataException extends RuntimeException {

    public CustomInvalidDataException(String message) {
        super(message);
    }

    public CustomInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
