package com.vevohub.integrator.api.user;

import com.vevohub.integrator.api.security.JwtIssuer;
import com.vevohub.integrator.logic.UserService;
import com.vevohub.integrator.models.LoginRequest;
import com.vevohub.integrator.models.LoginResponse;
import com.vevohub.integrator.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser); // In real applications, don't return the full user object
    }


    @PostMapping("/auth/login")
    public LoginResponse loginResponse(@RequestBody @Validated LoginRequest request) {
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return LoginResponse.builder().accessToken(token).build();
    }

    @GetMapping("/test")
    public ResponseEntity<?> testEndpoint() {
        return ResponseEntity.ok("{\"message\": \"test\"}");
    }
}
