package com.vevohub.integrator.service;

import com.vevohub.integrator.api.models.LoginResponse;
import com.vevohub.integrator.api.security.JwtIssuer;
import com.vevohub.integrator.api.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String email, String password) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        var principal = (UserPrincipal) auth.getPrincipal();

        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponse.builder().accessToken(token).build();
    }
}
