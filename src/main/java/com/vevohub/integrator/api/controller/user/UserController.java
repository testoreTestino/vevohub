package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.database.dto.UserEmailUserNameDto;
import com.vevohub.integrator.database.entity.UserEntity;
import com.vevohub.integrator.service.AuthService;
import com.vevohub.integrator.service.CandidatesService;
import com.vevohub.integrator.service.UserService;
import com.vevohub.integrator.api.models.LoginRequest;
import com.vevohub.integrator.api.models.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/user")
    public Optional<UserEmailUserNameDto> findUserByEmail(@RequestParam() String email) {
        return userService.findUserByEmail(email);
    }

}
