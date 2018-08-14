package com.codesquad.codereviewers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GithubUserinfoService {
    private static final String HEADER_PREFIX = "token ";

    @Value("${github.userinfo.endpoint}")
    private String userinfoEndpoint;

    @Value("${github.token.endpoint}")
    private String tokenExchangeEndpoint;

    public GithubUserProperty fetchUserProperty(String token) {
        RestTemplate template = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", generateHeaders(token));
        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};

        ResponseEntity<Map<String, String>> responseEntity = template.exchange(userinfoEndpoint, HttpMethod.GET, entity, typeRef);
        Map<String, String> properties = responseEntity.getBody();

        return new GithubUserProperty(properties);
    }

    private HttpHeaders generateHeaders(String token) {
        HttpHeaders header = new HttpHeaders();

        header.set("Authorization", generateHeaderValue(token));
        return header;
    }

    private String generateHeaderValue(String token) {
        StringBuilder header = new StringBuilder(HEADER_PREFIX);
        header.append(token);

        return header.toString();
    }
}
