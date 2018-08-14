package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.controller.views.request.GithubLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PreAuthorizeFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AuthenticationSuccessHandler successHandler;

    protected PreAuthorizeFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public PreAuthorizeFilter(String processingUrl, AuthenticationSuccessHandler successHandler) {
        super(processingUrl);
        this.successHandler = successHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        GithubLoginRequest request = objectMapper.readValue(httpServletRequest.getReader(), GithubLoginRequest.class);
        return super.getAuthenticationManager().authenticate(request.toToken());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }
}
