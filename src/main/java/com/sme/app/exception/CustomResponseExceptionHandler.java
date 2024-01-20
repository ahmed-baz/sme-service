package com.sme.app.exception;

import com.sme.app.entity.AppException;
import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.AppExceptionRepo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final AppExceptionRepo appExceptionRepo;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(appResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AppExceptionResponse.class)
    public ResponseEntity<Object> handleAppException(AppExceptionResponse ex, WebRequest request) {
        return mapAppException(ex.getErrorKey(), ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorCode = AppErrorKeys.GENERAL_ERROR;
        if (ex.getBindingResult().getFieldError() != null
                && ex.getBindingResult().getFieldError().getDefaultMessage() != null) {
            errorCode = ex.getBindingResult().getFieldError().getDefaultMessage();
        }
        return mapAppException(errorCode, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> mapAppException(String errorKey, HttpStatus httpStatus) {
        Optional<AppException> appException = appExceptionRepo.findByCode(errorKey);
        if (appException.isPresent()) {
            AppResponse appResponse = new AppResponse(new Date(), httpStatus, appException.get().getMessage(), errorKey);
            return new ResponseEntity(appResponse, httpStatus);
        }
        AppResponse appResponse = new AppResponse(new Date(), errorKey.toLowerCase().replace("_", " "), errorKey);
        return new ResponseEntity(appResponse, HttpStatus.BAD_REQUEST);
    }

}
