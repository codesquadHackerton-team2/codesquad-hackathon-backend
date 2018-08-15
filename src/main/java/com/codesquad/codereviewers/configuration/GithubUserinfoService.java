package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.configuration.security.StringUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Logger log = LoggerFactory.getLogger(GithubUserinfoService.class);
    private static final String HEADER_PREFIX = "token ";

    @Autowired
    private StringUtils utils;

    @Value("${github.userinfo.endpoint}")
    private String userinfoEndpoint;

    @Value("${github.token.endpoint}")
    private String tokenExchangeEndpoint;

    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.client_secret}")
    private String clientSecret;


    public GithubUserProperty fetchUserProperty(String token) {
        log.info("processing authentication request for token {}", token);

        RestTemplate template = new RestTemplate();
        String accessToken = fetchUserToken(token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", generateHeaders(accessToken));
        log.info("generated request parameters: {}", entity);
        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};

        ResponseEntity<Map<String, String>> responseEntity = template.exchange(userinfoEndpoint, HttpMethod.GET, entity, typeRef);
        Map<String, String> properties = responseEntity.getBody();

        return new GithubUserProperty(properties);
    }

    public String fetchUserToken(String originalToken) {
        RestTemplate template = new RestTemplate();
        HttpEntity<Map<String, String>> request = new HttpEntity<>(generatePostMap(originalToken));

        ResponseEntity<String> response = template.postForEntity(tokenExchangeEndpoint, request, String.class);
        log.info("response header: {}", response.getHeaders());
        log.info("response body: {}", response.getBody());
        return utils.parseAccessToken(response.getBody());
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

    private Map<String, String> generatePostMap(String code) {
        Map<String, String> requestParams = Maps.newHashMap();

        requestParams.put("client_id", clientId);
        requestParams.put("client_secret", clientSecret);
        requestParams.put("code", code);
        log.info("generated request parameters: {}", requestParams);

        return requestParams;
    }
}
