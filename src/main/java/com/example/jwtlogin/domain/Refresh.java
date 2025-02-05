package com.example.jwtlogin.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Refresh {
    private Long id;
    private String username;
    private String refreshToken;
    private LocalDateTime expiration;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public static Refresh of(String username, String refreshToekn, Long expiredMs) {
        return Refresh.builder()
                .username(username)
                .refreshToken(refreshToekn)
                .expiration(LocalDateTime.now().plus(Duration.ofMillis(expiredMs)))
                .build();
    }
}
