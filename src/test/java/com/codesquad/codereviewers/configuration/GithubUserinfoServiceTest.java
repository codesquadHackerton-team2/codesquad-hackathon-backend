package com.codesquad.codereviewers.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GithubUserinfoServiceTest {
    private final Logger log = LoggerFactory.getLogger(GithubUserinfoServiceTest.class);

    @Autowired
    private GithubUserinfoService userinfoService;

    @Test
    public void test_fetch_github_token() {
        String token = "c76852c7fe5ba4935323";

        String fetchedToken = userinfoService.fetchUserToken(token);
        log.info(fetchedToken);
    }

    @Test
    public void test_fetch_github_userinfo() {
        String token = "b3cfe79e854c77ec1ba09ac46f66e05c66439b5b";

        GithubUserProperty property = userinfoService.fetchUserProperty(token);
        assertThat(property.getDescription(), is("Being awesome Front-end developer"));
    }
}
