package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.exception.ApiErrorResponse;
import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.utils.MessageHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.DuplicateEntityException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(CustomException.DuplicateEntityException ex) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse("error-10001", ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
