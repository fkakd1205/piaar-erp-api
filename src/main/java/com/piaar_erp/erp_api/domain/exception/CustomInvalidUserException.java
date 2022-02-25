package com.piaar_erp.erp_api.domain.exception;

/**
 * invalid user exception
 * http status 401
 */
public class CustomInvalidUserException extends RuntimeException{
    public CustomInvalidUserException(String message) {
        super(message);
    }
    public CustomInvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
