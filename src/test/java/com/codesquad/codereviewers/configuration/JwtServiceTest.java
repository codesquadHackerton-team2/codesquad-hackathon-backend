package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.configuration.security.LoggedOnToken;
import com.codesquad.codereviewers.domain.RegisteredUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtServiceTest {
    private final Logger log = LoggerFactory.getLogger(JwtServiceTest.class);

    @Autowired
    private JwtService jwtService;
    private RegisteredUser user;

    @Before
    public void setUp() {
        this.user = RegisteredUser.builder().userId(10000L).nickname("bomeehouse").build();
    }

    @Test
    public void test_jwt_generate() {
        log.info(jwtService.generateToken(new LoggedOnToken(user, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")))));
    }


}