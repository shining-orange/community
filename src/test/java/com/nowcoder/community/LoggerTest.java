package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-14 22:48
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTest {
    private static final Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void  testLogger(){
        System.out.println(logger.getName());
        logger.debug("debug logger");
        logger.info("info logger");
        logger.warn("warn logger");
        logger.error("error logger");
    }
}