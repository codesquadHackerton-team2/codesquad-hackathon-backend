package com.codesquad.codereviewers.domain;

public class UnregisteredUser extends RegisteredUser {

    @Override
    public boolean isRegistered() {
        return false;
    }
}
