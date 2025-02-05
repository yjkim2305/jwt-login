package com.example.jwtlogin.application.service;

import com.example.jwtlogin.application.repository.RefreshTokenRepository;
import com.example.jwtlogin.common.exception.CoreException;
import com.example.jwtlogin.common.jwt.util.JwtUtil;
import com.example.jwtlogin.domain.Refresh;
import com.example.jwtlogin.domain.exception.UserErrorType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public void addRefresh(String username, String refreshToken, Long expiredMs) {
        refreshTokenRepository.save(Refresh.of(username, refreshToken, expiredMs));
    }

    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            throw new CoreException(UserErrorType.RefreshTokenIsNull);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new CoreException(UserErrorType.ExpireRefreshToken);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            throw new CoreException(UserErrorType.InvalidRefreshToken);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshTokenRepository.existsByRefreshToken(refresh);
        if (!isExist) {
            throw new CoreException(UserErrorType.InvalidRefreshToken);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshTokenRepository.deleteByRefreshToken(refresh);
        refreshTokenRepository.save(Refresh.of(username, newRefresh, 86400000L));

        //response
        response.setHeader("access", newAccess);
        response.addCookie(jwtUtil.createCookie("refresh", newRefresh));
    }
}
