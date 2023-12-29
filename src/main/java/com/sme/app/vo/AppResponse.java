package com.sme.app.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter
public class AppResponse<T> {
    private Date responseDate;
    private HttpStatus status;
    private String message;
    private String details;
    private String errorCode;
    private T data;

    public AppResponse() {
        this.message = "success";
        this.responseDate = new Date();
        this.status = HttpStatus.OK;
        this.errorCode = "0";
    }

    public AppResponse(T t) {
        this.data = t;
        this.responseDate = new Date();
        this.status = HttpStatus.OK;
        this.errorCode = "0";
    }

    public AppResponse(Date responseDate, String message, String details) {
        this.responseDate = responseDate;
        this.message = message;
        this.details = details;
    }

    public AppResponse(Date responseDate, HttpStatus status, String message, String details) {
        this.responseDate = responseDate;
        this.status = status;
        this.message = message;
        this.details = details;
    }

}
