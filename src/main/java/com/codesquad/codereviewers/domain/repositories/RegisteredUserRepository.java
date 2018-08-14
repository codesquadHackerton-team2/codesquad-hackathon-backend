package com.codesquad.codereviewers.domain.repositories;

import com.codesquad.codereviewers.domain.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByGithubCode(String githubCode);
    Optional<RegisteredUser> findByEmail(String email);
}
