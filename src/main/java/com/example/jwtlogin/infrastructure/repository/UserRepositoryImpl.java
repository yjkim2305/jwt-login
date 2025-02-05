package com.example.jwtlogin.infrastructure.repository;

import com.example.jwtlogin.application.repository.UserRepository;
import com.example.jwtlogin.domain.User;
import com.example.jwtlogin.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(User::from)
                .orElse(null);
    }

    @Override
    public User save(User user) {
        return User.from(userJpaRepository.save(UserEntity.toEntity(user)));
    }
}
