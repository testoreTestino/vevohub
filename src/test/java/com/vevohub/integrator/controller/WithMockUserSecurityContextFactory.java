package com.vevohub.integrator.controller;

import com.vevohub.integrator.api.security.UserAuthToken;
import com.vevohub.integrator.api.security.UserPrincipal;
import lombok.With;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        var authorities = Arrays.stream(annotation.authorities()).map(SimpleGrantedAuthority::new).toList();

        var principle = UserPrincipal.builder().userId(annotation.userId()).email("fake@fakeEmail.com").authorities(authorities).build();

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UserAuthToken(principle));

        return context;
    }
}
