package com.example.jwtlogin.infrastructure.repository;

import com.example.jwtlogin.infrastructure.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshJpaRepository extends JpaRepository<RefreshEntity, Long> {
    Boolean existsByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
