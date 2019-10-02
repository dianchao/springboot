package com.dianchao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLogAppTests {
    //日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoads() {
        //日志的级别，日志级别由低到高 trace<debug<info<warn<error
        //可以调整输出的日志级别，日志就会在这个级别以后的高级别生效
        //spring boot的默认日志级别是info，没有指定级别就用SpringBoot默认规定的级别：root级别
        logger.trace("这是trace日志...");
        logger.debug("这是debugger日志...");

        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }

}
