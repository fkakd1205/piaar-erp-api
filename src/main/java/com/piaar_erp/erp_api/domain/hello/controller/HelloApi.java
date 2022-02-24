package com.piaar_erp.erp_api.domain.hello.controller;

import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloApi {
    @GetMapping("")
    public ResponseEntity<?> searchHello(){
        Message message = new Message();

        message.setStatus(HttpStatus.OK);
        message.setMessage("success");
        message.setData("hello");

        return new ResponseEntity<>(message, message.getStatus());
    }
}
