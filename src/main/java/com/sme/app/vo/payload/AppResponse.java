package com.sme.app.vo.payload;

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

    public AppResponse(Date responseDate, String message, String errorCode) {
        this.responseDate = responseDate;
        this.message = message;
        this.errorCode = errorCode;
    }

    public AppResponse(String errorCode, HttpStatus status) {
        this.status = status;
        this.responseDate = new Date();
        this.errorCode = errorCode;
    }

    public AppResponse(Date responseDate, HttpStatus status, String message, String errorCode) {
        this.responseDate = responseDate;
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

}
