package com.demo.user.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter
public class AppResponse<T> {
    private Date responseDate;
    private HttpStatus httpStatus;
    private String message;
    private String details;
    private T data;

    public AppResponse() {
        this.message = "success";
        this.responseDate = new Date();
        this.httpStatus = HttpStatus.OK;
    }

    public AppResponse(T t) {
        this.data = t;
        this.responseDate = new Date();
        this.httpStatus = HttpStatus.OK;
    }

    public AppResponse(Date responseDate, String message, String details) {
        this.responseDate = responseDate;
        this.message = message;
        this.details = details;
    }

    public AppResponse(Date responseDate, HttpStatus httpStatus, String message, String details) {
        this.responseDate = responseDate;
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
    }

}
