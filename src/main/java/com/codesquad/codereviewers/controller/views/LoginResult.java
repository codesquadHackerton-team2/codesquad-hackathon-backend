package com.codesquad.codereviewers.controller.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LoginResult {

    @JsonProperty("issued_token")
    private String token;

}
