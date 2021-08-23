package com.bguf.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author gufb
 * @date 2021/8/20 2:49 PM
 */
@Slf4j
@Component
public class Cat implements InitializingBean, DisposableBean{

    public Cat() {
        log.info("cat constructor");
    }

    @Override
    public void destroy() throws Exception {
        log.info("cat destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("cat init");
    }
}
