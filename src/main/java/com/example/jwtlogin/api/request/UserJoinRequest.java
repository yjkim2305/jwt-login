package com.example.jwtlogin.api.request;

import com.example.jwtlogin.common.domain.enums.RoleType;

public record UserJoinRequest(
        String username,
        String password,
        RoleType role
) {
}
