package com.example.jwtlogin.api;

import com.example.jwtlogin.api.response.UserJoinResponse;
import com.example.jwtlogin.common.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> userJoin(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromRequest(request);
        String username = jwtUtil.getUsername(token);
        System.out.println(username);
        return ResponseEntity.ok().build();
    }

}
