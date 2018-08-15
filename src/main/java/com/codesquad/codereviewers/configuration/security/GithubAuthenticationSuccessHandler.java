package com.codesquad.codereviewers.configuration.security;

import com.codesquad.codereviewers.configuration.JwtService;
import com.codesquad.codereviewers.controller.views.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GithubAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoggedOnToken token = (LoggedOnToken)authentication;

        LoginResult result = LoginResult.builder().token(jwtService.generateToken(token)).build();
        mapper.writeValue(httpServletResponse.getWriter(), result);
    }
}
