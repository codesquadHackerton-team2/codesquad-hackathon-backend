package com.codesquad.codereviewers.controller.views.request;

import com.codesquad.codereviewers.configuration.security.GithubTokenAuthenticationToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class GithubLoginRequest {

    @JsonProperty("token_value")
    private String token;

    public GithubTokenAuthenticationToken toToken() {
        return new GithubTokenAuthenticationToken(this.token);
    }


}
