package com.codesquad.codereviewers.configuration.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class StringUtilsTest {
    private final Logger log = LoggerFactory.getLogger(StringUtilsTest.class);

    private final StringUtils utils = new StringUtils();
    private final String originalString = "access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer";

    @Test
    public void test_string_token() {
        log.info(utils.parseAccessToken(originalString));
    }

}