package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.domain.RegisteredUser;

public interface SocialUserProperty {
    String getName();
    String getProfileHref();
    String getNickname();

    RegisteredUser generateEntity();
}
