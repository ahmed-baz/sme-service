package com.sme.app.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class AppExceptionResponse extends RuntimeException {

    private String errorKey;
    private HttpStatus httpStatus;

    public AppExceptionResponse() {
        this.errorKey = AppErrorKeys.GENERAL_ERROR;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public AppExceptionResponse(String errorKey, HttpStatus httpStatus) {
        this.errorKey = errorKey;
        this.httpStatus = httpStatus;
    }
}
