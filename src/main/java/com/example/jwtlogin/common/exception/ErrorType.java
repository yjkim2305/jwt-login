package com.example.jwtlogin.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String getErrorCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
