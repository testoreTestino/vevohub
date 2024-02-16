package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.api.security.JwtIssuer;
import com.vevohub.integrator.api.security.UserPrincipal;
import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.database.entity.UserEntity;
import com.vevohub.integrator.service.AuthService;
import com.vevohub.integrator.service.CandidatesService;
import com.vevohub.integrator.service.UserService;
import com.vevohub.integrator.api.models.LoginRequest;
import com.vevohub.integrator.api.models.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final AuthService authService;

    @Autowired
    private CandidatesService candidatesService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        UserEntity registeredUserEntity = userService.registerUser(user);
        return ResponseEntity.ok(registeredUserEntity); // In real applications, don't return the full user object
    }

    @PostMapping("/auth/login")
    public LoginResponse loginResponse(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @GetMapping("/test")
    public String testEndpoint(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this " + principal.getEmail() + " User ID:" + principal.getUserId();
    }

    @GetMapping("/")
    public String greeting() {
        return "Hello";
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/candidates")
    public List<CandidatesEntity> getCandidates() {
        return candidatesService.findAll();
    }
}
