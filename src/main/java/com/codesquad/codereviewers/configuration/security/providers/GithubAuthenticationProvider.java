package com.codesquad.codereviewers.configuration.security.providers;

import com.codesquad.codereviewers.configuration.GithubUserProperty;
import com.codesquad.codereviewers.configuration.GithubUserinfoService;
import com.codesquad.codereviewers.configuration.security.GithubTokenAuthenticationToken;
import com.codesquad.codereviewers.configuration.security.LoggedOnToken;
import com.codesquad.codereviewers.domain.RegisteredUser;
import com.codesquad.codereviewers.domain.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private GithubUserinfoService userinfoService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GithubTokenAuthenticationToken token = (GithubTokenAuthenticationToken)authentication;
        GithubUserProperty property = userinfoService.fetchUserProperty(token.getCredentials());

        RegisteredUser user = registeredUserService.getUserByEmail(property.getEmail());
        if(!user.isRegistered()) {
            RegisteredUser generatedUser = property.generateEntity();
            user = registeredUserService.saveUser(generatedUser);
        }
        return new LoggedOnToken(user, generateAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GithubTokenAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private List<SimpleGrantedAuthority> generateAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
