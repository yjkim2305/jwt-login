package com.example.jwtlogin.application.service;

import com.example.jwtlogin.application.repository.RefreshTokenRepository;
import com.example.jwtlogin.domain.Refresh;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public Boolean existsByRefreshToken(String refreshToken) {
        return refreshTokenRepository.existsByRefreshToken(refreshToken);
    }

    @Transactional
    public void deleteByRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    public void addRefresh(String username, String refreshToken, Long expiredMs) {
        refreshTokenRepository.save(Refresh.of(username, refreshToken, expiredMs));
    }
}
