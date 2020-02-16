package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.exception.ApiErrorResponse;
import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.utils.MessageHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @ExceptionHandler(CustomException.DuplicateEntityException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(CustomException.DuplicateEntityException ex) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse("error-10001", ex.getMessage(),dateFormat.format(new Date()),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", dateFormat.format(new Date()));
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors",errors);

        return new ResponseEntity<>(body,headers,status);



    }
}
