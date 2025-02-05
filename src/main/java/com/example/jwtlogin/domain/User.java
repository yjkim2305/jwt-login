package com.example.jwtlogin.domain;

import com.example.jwtlogin.application.service.dto.UserJoinDto;
import com.example.jwtlogin.common.domain.enums.RoleType;
import com.example.jwtlogin.infrastructure.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String username;
    private String password;
    private RoleType role;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public static User from(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .createdDate(userEntity.getCreatedDate())
                .updateDate(userEntity.getUpdatedDate())
                .build();
    }

    public static User fromByJoinDto(UserJoinDto userJoinDto, String encryptedPassword) {
        return User.builder()
                .username(userJoinDto.username())
                .password(encryptedPassword)
                .role(userJoinDto.role())
                .build();
    }
}
