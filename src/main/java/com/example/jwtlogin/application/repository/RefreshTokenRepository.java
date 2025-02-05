package com.example.jwtlogin.application.repository;

import com.example.jwtlogin.domain.Refresh;

public interface RefreshTokenRepository {
    Boolean existsByRefreshToken(String refreshToken);
    void deleteByRefreshToken(String refreshToken);
    void save(Refresh refresh);

}
