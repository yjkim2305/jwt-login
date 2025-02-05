package com.example.jwtlogin.infrastructure.repository;

import com.example.jwtlogin.application.repository.RefreshTokenRepository;
import com.example.jwtlogin.domain.Refresh;
import com.example.jwtlogin.infrastructure.entity.RefreshEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshJpaRepository refreshJpaRepository;

    @Override
    public Boolean existsByRefreshToken(String refreshToken) {
        return refreshJpaRepository.existsByRefreshToken(refreshToken);
    }

    @Override
    public void deleteByRefreshToken(String refreshToken) {
        refreshJpaRepository.deleteByRefreshToken(refreshToken);
    }

    @Override
    public void save(Refresh refresh) {
        refreshJpaRepository.save(RefreshEntity.toEntity(refresh));
    }
}
