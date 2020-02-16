package com.payam.learn.usermanagement.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
private String error;
private String message;
private String timestamp;
private int status;

}
