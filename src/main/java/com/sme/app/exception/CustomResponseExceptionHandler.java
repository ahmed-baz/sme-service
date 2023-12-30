package com.sme.app.exception;

import com.sme.app.model.AppException;
import com.sme.app.repo.AppExceptionRepo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final AppExceptionRepo appExceptionRepo;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(appResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(AppExceptionResponse.class)
    public ResponseEntity<Object> handleAppException(AppExceptionResponse ex, WebRequest request) {
        AppException appException = appExceptionRepo.findByCode(ex.getErrorKey());
        AppResponse appResponse = new AppResponse(new Date(), ex.getHttpStatus(), appException.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(appResponse, ex.getHttpStatus());
        return responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getBindingResult().getFieldError().getDefaultMessage());
        ResponseEntity responseEntity = new ResponseEntity(appResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}
