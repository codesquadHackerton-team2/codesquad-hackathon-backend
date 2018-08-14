package com.codesquad.codereviewers.configuration.security;

import com.codesquad.codereviewers.domain.RegisteredUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoggedOnToken extends UsernamePasswordAuthenticationToken {

    public LoggedOnToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public LoggedOnToken(RegisteredUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user, user.getEmail(), authorities);
    }

    @Override
    public RegisteredUser getPrincipal() {
        return (RegisteredUser)super.getPrincipal();
    }
}
