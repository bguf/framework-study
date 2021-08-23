package com.bguf.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gufb
 * @date 2021/8/20 2:12 PM
 */
@Slf4j
@Component
public class Car {
    public Car() {
        log.info("car constructor");
    }

    public void init() {
        log.info("car......init......");
    }

    public void destroy() {
        log.info("car......destroy......");
    }
}
