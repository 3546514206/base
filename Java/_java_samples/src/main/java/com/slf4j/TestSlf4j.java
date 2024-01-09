package com.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author landyl
 * @create 5:15 PM 04/07/2018
 */
public class TestSlf4j {
    private static final Logger logger = LoggerFactory.getLogger(TestSlf4j.class);
    public static void main(String[] args) {
        int status = 0;
        if (status == 0) {
            logger.info("status:{}", status);
        } else {
            logger.info("status:{}", status);
        }
        logger.info("end!");
    }
}
