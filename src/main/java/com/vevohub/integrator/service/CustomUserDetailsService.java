package com.vevohub.integrator.service;

import com.vevohub.integrator.api.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userService.findByEmail(userName).orElseThrow();

        //TODO: Modify hardcoded value when we design roles in the UI.

        user.setRole("USER_ADMIN");

        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
                .password(user.getPasswordHash())
                .build();
    }
}
