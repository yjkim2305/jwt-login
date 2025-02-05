package com.example.jwtlogin.domain.exception;

import com.example.jwtlogin.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum UserErrorType implements ErrorType {

    ExistUserException(HttpStatus.OK, "이미 회원이 존재합니다"),
    ExpireAccessToken(HttpStatus.UNAUTHORIZED, "access 토큰이 만료되었습니다."),
    InvalidAccessToken(HttpStatus.UNAUTHORIZED, "적합하지 않은 access 토큰입니다."),
    RefreshTokenIsNull(HttpStatus.UNAUTHORIZED, "refresh 토큰이 존재하지 않습니다."),
    ExpireRefreshToken(HttpStatus.UNAUTHORIZED, "refresh 토큰이 만료되었습니다."),
    InvalidRefreshToken(HttpStatus.UNAUTHORIZED, "적합하지 않은 refresh 토큰입니다."),
    ;



    private final HttpStatus httpStatus;
    private final String message;

    UserErrorType(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
