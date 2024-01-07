package com.vevohub.integrator.api.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserAuthToken extends AbstractAuthenticationToken {

    private final UserPrincipal userPrincipal;

    public UserAuthToken(UserPrincipal userPrincipal) {
        super(userPrincipal.getAuthorities());
        this.userPrincipal = userPrincipal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return userPrincipal;
    }
}
