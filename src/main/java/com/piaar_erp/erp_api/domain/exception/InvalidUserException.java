package com.piaar_erp.erp_api.domain.exception;

/**
 * invalid user exception
 * http status 401
 */
public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message) {
        super(message);
    }
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
