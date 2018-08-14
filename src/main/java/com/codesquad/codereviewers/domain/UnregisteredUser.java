package com.codesquad.codereviewers.domain;

public class UnregisteredUser extends RegisteredUser {

    private String uuid;

    public UnregisteredUser(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean isRegistered() {
        return false;
    }
}
