package com.codesquad.codereviewers.configuration.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class GithubTokenAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public GithubTokenAuthenticationToken(String principal, String credentials) {
        super(principal, credentials);
    }

    public GithubTokenAuthenticationToken(String credential) {
        super("GITHUB_USER", credential);
    }

    @Override
    public String getPrincipal() {
        return (String)super.getPrincipal();
    }

    @Override
    public String getCredentials() {
        return (String)super.getCredentials();
    }
}
