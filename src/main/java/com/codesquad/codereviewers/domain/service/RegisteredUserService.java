package com.codesquad.codereviewers.domain.service;

import com.codesquad.codereviewers.configuration.UUIDUtil;
import com.codesquad.codereviewers.domain.RegisteredUser;
import com.codesquad.codereviewers.domain.UnregisteredUser;
import com.codesquad.codereviewers.domain.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class RegisteredUserService {

    @Autowired
    private UUIDUtil uuidUtil;

    @Autowired
    private RegisteredUserRepository repository;

    @Transactional(readOnly = true)
    public RegisteredUser getUserById(Long id) {
        return repository.findById(id).orElseThrow(supplyException());
    }

    @Transactional(readOnly = true)
    public RegisteredUser getUserByGithubCode(String githubCode) {
        return repository.findByGithubCode(githubCode).orElse(new UnregisteredUser(uuidUtil.generateUUID()));
    }

    @Transactional(readOnly = true)
    public RegisteredUser getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(supplyException());
    }

    @Transactional(rollbackFor = NullPointerException.class)
    public RegisteredUser saveUser(RegisteredUser user) {
        return repository.save(user);
    }

    private class NoUserFoundException extends RuntimeException {
        protected NoUserFoundException(String msg) {
            super(msg);
        }
    }

    private Supplier<NoUserFoundException> supplyException() {
        return () -> new NoUserFoundException("검색된 회원이 없습니다");
    }
}
