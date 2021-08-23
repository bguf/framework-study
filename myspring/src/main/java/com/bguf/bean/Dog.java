package com.bguf.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author gufb
 * @date 2021/8/20 3:11 PM
 */
@Slf4j
@Component
public class Dog implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    public Dog() {
        log.info("dog constructor");
    }

    @PostConstruct
    public void init() {
        log.info("dog init");
    }

    @PreDestroy
    public void destroy() {
        log.info("dog destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
