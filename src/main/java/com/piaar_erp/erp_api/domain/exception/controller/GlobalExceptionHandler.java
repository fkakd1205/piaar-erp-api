package com.piaar_erp.erp_api.domain.exception.controller;

import javax.validation.ConstraintViolationException;

import com.piaar_erp.erp_api.domain.message.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   /**
    * 데이터 베이스 Valid 유효성 검사 예외가 발생했을 때
    * http status 409
    */
   @ExceptionHandler({ ConstraintViolationException.class })
   public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
       log.error("ERROR STACKTRACE => {}", e.getStackTrace());

       Message message = new Message();
       message.setStatus(HttpStatus.CONFLICT);
       message.setMessage("data_error");
       message.setMemo("입력된 데이터를 등록할 수 없습니다.\n 수정 후 재등록해주세요.");

       return new ResponseEntity<>(message, message.getStatus());
   }

   /**
    * 데이터 베이스 관련 예외가 발생했을 때
    * http status 400
    */
   @ExceptionHandler({ DataAccessException.class })
   public ResponseEntity<?> dataAccessExceptionHandler(DataAccessException e) {
       log.error("ERROR STACKTRACE => {}", e.getStackTrace());
       log.error("ERROR ROOTCAUSE => {}", e.getRootCause());

       Message message = new Message();
       message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
       message.setMessage("undefined_error");
       message.setMemo("알 수 없는 에러가 발생했습니다. 관리자에게 문의하세요.");

       return new ResponseEntity<>(message, message.getStatus());
   }
}
