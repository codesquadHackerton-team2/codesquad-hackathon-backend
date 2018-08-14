package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.domain.RegisteredUser;
import com.google.common.collect.Maps;

import java.util.Map;

public class GithubUserProperty implements SocialUserProperty {

    private Map<String, String> properties;

    public GithubUserProperty(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String getName() {
        return properties.get("name");
    }

    @Override
    public String getProfileHref() {
        return properties.get("avatar_url");
    }

    @Override
    public String getNickname() {
        return properties.get("login");
    }

    @Override
    public RegisteredUser generateEntity() {
        return RegisteredUser.builder().username(getName()).nickname(getNickname()).profileHref(getProfileHref()).build();
    }
}
