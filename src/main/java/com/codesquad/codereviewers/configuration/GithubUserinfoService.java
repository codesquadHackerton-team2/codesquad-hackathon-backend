package com.codesquad.codereviewers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubUserinfoService {
    private static final String HEADER_PREFIX = "token ";

    @Value("${github.userinfo.endpoint}")
    private String userinfoEndpoint;

    @Value("${github.token.endpoint}")
    private String tokenExchangeEndpoint;
}
