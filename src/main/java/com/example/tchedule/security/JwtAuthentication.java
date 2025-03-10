package com.example.tchedule.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

public class JwtAuthentication extends AbstractAuthenticationToken {
    private final String username;

    public JwtAuthentication(String username) {
        super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        this.username = username;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
