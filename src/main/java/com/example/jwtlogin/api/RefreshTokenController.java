package com.example.jwtlogin.api;

import com.example.jwtlogin.application.service.RefreshTokenService;
import com.example.jwtlogin.common.jwt.util.JwtUtil;
import com.example.jwtlogin.infrastructure.entity.RefreshEntity;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        refreshTokenService.reissueToken(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
