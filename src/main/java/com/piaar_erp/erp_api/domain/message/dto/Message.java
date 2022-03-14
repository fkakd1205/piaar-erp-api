package com.piaar_erp.erp_api.domain.message.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

import com.piaar_erp.erp_api.domain.page.dto.Pagination;

@Data
public class Message {
    private HttpStatus status;
    private int statusCode;
    private String statusMessage;
    private String message;
    private String memo;
    private Object data;
    private String path;
    private Date timestamp;
    private String error;
    private Pagination page;

    public Message() {
        this.status = HttpStatus.BAD_REQUEST;
        this.statusCode = this.status.value();
        this.statusMessage = this.status.name();
        this.message = null;
        this.memo = null;
        this.data = null;
        this.timestamp = new Date();
        this.page = null;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
        this.statusCode = status.value();
        this.statusMessage = status.name();
    }
}
