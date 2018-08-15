package com.codesquad.codereviewers.configuration.security;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public String parseAccessToken(String parseResult) {
        String[] detachedStrings = parseResult.split("&");

        return detachedStrings[0].split("=")[1];
    }
}
