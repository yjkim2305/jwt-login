package com.example.jwtlogin.common.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
