package com.example.jwtlogin.application.service.dto;

import com.example.jwtlogin.api.request.UserJoinRequest;
import com.example.jwtlogin.common.domain.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record UserJoinDto(
        String username,
        String password,
        RoleType role
) {

    public static UserJoinDto from(UserJoinRequest rq) {
        return UserJoinDto.builder()
                .username(rq.username())
                .password(rq.password())
                .role(rq.role())
                .build();

    }
}
