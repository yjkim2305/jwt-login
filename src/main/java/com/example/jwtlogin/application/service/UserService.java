package com.example.jwtlogin.application.service;

import com.example.jwtlogin.application.repository.UserRepository;
import com.example.jwtlogin.application.service.dto.UserJoinDto;
import com.example.jwtlogin.common.exception.CoreException;
import com.example.jwtlogin.domain.User;
import com.example.jwtlogin.domain.exception.UserErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User join(UserJoinDto userJoinDto) {

        Boolean isExist = userRepository.existsByUsername(userJoinDto.username());

        if (isExist) {
            throw new CoreException(UserErrorType.ExistUserException);
        }

        return userRepository.save(User.fromByJoinDto(userJoinDto, bCryptPasswordEncoder.encode(userJoinDto.password())));
    }
}
