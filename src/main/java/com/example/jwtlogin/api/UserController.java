package com.example.jwtlogin.api;

import com.example.jwtlogin.api.request.UserJoinRequest;
import com.example.jwtlogin.api.response.UserJoinResponse;
import com.example.jwtlogin.application.service.UserService;
import com.example.jwtlogin.application.service.dto.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserJoinResponse> userJoin(@RequestBody UserJoinRequest rq) {
        return ResponseEntity.ok(UserJoinResponse.from(userService.join(UserJoinDto.from(rq))));
    }

}
