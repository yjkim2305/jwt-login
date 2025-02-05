package com.example.jwtlogin.common.jwt.filter;

import com.example.jwtlogin.common.domain.enums.RoleType;
import com.example.jwtlogin.common.exception.CoreException;
import com.example.jwtlogin.common.jwt.dto.CustomUserDetails;
import com.example.jwtlogin.common.jwt.util.JwtUtil;
import com.example.jwtlogin.domain.User;
import com.example.jwtlogin.domain.exception.UserErrorType;
import com.example.jwtlogin.infrastructure.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access");

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            throw new CoreException(UserErrorType.ExpireAccessToken);
        }

        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            throw new CoreException(UserErrorType.InvalidAccessToken);
        }

        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);


        CustomUserDetails customUserDetails = new CustomUserDetails(User.of(username, RoleType.valueOf(role)));

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}
