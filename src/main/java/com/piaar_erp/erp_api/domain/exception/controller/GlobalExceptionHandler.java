package com.piaar_erp.erp_api.domain.exception.controller;

import com.piaar_erp.erp_api.domain.exception.AccessDeniedException;
import com.piaar_erp.erp_api.domain.exception.ExcelFileUploadException;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
    @ExceptionHandler({ FileUploadException.class })
    public ResponseEntity<?> FileUploadExceptionHandler(FileUploadException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setMessage("file_upload_error");
        message.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }

    @ExceptionHandler({ ExcelFileUploadException.class })
    public ResponseEntity<?> DeliveryReadyExceptionHandler(ExcelFileUploadException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.BAD_REQUEST);
        message.setMessage("data_error");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * 유저 접근 권한이 없을때
     * http status 403
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> AccessDeniedExceptionHandler(AccessDeniedException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.FORBIDDEN);
        message.setMessage("access_denied");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }
}
