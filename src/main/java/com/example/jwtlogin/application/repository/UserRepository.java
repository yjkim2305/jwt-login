package com.example.jwtlogin.application.repository;

import com.example.jwtlogin.domain.User;

public interface UserRepository {
    Boolean existsByUsername(String username);
    User findByUsername(String username);

    User save(User user);
}
