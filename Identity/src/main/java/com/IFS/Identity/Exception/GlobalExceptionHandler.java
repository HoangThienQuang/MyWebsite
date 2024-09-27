package com.IFS.Identity.Exception;

import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.response.ApiResponseError;
import com.IFS.Identity.dto.response.ApiResponseSuccess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    //default exception
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponseError> defaultException(Exception exception)
    {
        ApiResponseError apiResponseError = new ApiResponseError();
        apiResponseError.setCode(ResponseCode.NOT_IMPLEMENT_EXCEPTION.getCode()); 
        apiResponseError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponseError);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponseError> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception)
    {
        ApiResponseError apiResponseError = new ApiResponseError();

        String errorKey = exception.getFieldError().getDefaultMessage();
        ResponseCode errorCode = ResponseCode.valueOf(errorKey);

        apiResponseError.setCode(errorCode.getCode());
        apiResponseError.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponseError);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponseError> handlerAppException(AppException exception)
    {
        ApiResponseError apiResponseError = new ApiResponseError();

        apiResponseError.setCode(exception.getResponseCode().getCode());
        apiResponseError.setMessage(exception.getResponseCode().getMessage());

        return ResponseEntity.badRequest().body(apiResponseError);
    }

}
