package com.piaar_erp.erp_api.domain.exception.controller;

import com.piaar_erp.erp_api.domain.exception.CustomAccessDeniedException;
import com.piaar_erp.erp_api.domain.exception.CustomExcelFileUploadException;
import com.piaar_erp.erp_api.domain.exception.CustomInvalidDataException;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalCustomExceptionHandler {
    @ExceptionHandler({ CustomExcelFileUploadException.class })
    public ResponseEntity<?> customExcelFileUploadExceptionHandler(CustomExcelFileUploadException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.BAD_REQUEST);
        message.setMessage("excel_data_error");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * 유저 접근 권한이 없을때
     * http status 403
     */
    @ExceptionHandler({ CustomAccessDeniedException.class })
    public ResponseEntity<?> customAccessDeniedExceptionHandler(CustomAccessDeniedException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.FORBIDDEN);
        message.setMessage("access_denied");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * 데이터를 찾을 수 없을 때
     * http status 403
     */
    @ExceptionHandler({ CustomNotFoundDataException.class })
    public ResponseEntity<?> customNotFoundExceptionHandler(CustomNotFoundDataException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.NOT_FOUND);
        message.setMessage("not_found");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * 유효한 데이터 형태가 아닐 때
     * http status 400
     */
    @ExceptionHandler({ CustomInvalidDataException.class })
    public ResponseEntity<?> customInvalidDataExceptionHandler(CustomInvalidDataException e) {
        log.error("ERROR STACKTRACE => {}", e.getStackTrace());

        Message message = new Message();
        message.setStatus(HttpStatus.BAD_REQUEST);
        message.setMessage("data_error");
        message.setMemo(e.getMessage());

        return new ResponseEntity<>(message, message.getStatus());
    }
}
