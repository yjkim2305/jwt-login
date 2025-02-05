package com.example.jwtlogin.infrastructure.entity;

import com.example.jwtlogin.common.domain.entity.BaseTimeEntity;
import com.example.jwtlogin.domain.Refresh;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "refresh")
public class RefreshEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String refreshToken;
    private LocalDateTime expiration;

    public static RefreshEntity toEntity(Refresh refresh) {
        return RefreshEntity.builder()
                .id(refresh.getId())
                .username(refresh.getUsername())
                .refreshToken(refresh.getRefreshToken())
                .expiration(refresh.getExpiration())
                .build();
    }
}
