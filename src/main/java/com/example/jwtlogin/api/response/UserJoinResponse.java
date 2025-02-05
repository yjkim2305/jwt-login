package com.example.jwtlogin.api.response;

import com.example.jwtlogin.api.request.UserJoinRequest;
import com.example.jwtlogin.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
public record UserJoinResponse(
        String username
) {

    public static UserJoinResponse from(User user) {
        return UserJoinResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
