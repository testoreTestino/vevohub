package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.api.security.JwtIssuer;
import com.vevohub.integrator.api.security.UserPrincipal;
import com.vevohub.integrator.database.entity.UserEntity;
import com.vevohub.integrator.database.service.UserService;
import com.vevohub.integrator.api.models.LoginRequest;
import com.vevohub.integrator.api.models.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    private final JwtIssuer jwtIssuer;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        UserEntity registeredUserEntity = userService.registerUser(user);
        return ResponseEntity.ok(registeredUserEntity); // In real applications, don't return the full user object
    }


    @PostMapping("/auth/login")
    public LoginResponse loginResponse(@RequestBody @Validated LoginRequest request) {
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return LoginResponse.builder().accessToken(token).build();
    }

    @GetMapping("/test")
    public String testEndpoint(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this " + principal.getEmail() + " User ID:" + principal.getUserId();
    }
}
