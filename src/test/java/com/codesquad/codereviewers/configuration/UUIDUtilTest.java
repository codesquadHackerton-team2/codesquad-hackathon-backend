package com.codesquad.codereviewers.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UUIDUtilTest {
    private final Logger log = LoggerFactory.getLogger(UUIDUtilTest.class);

    @Autowired
    private UUIDUtil uuidUtil;

    @Test
    public void test_uuid_generate() {
        log.info(uuidUtil.generateUUID());
    }
}