package com.bguf.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author gufb
 * @date 2021/8/18 4:10 PM
 */
@Component
@Slf4j
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware{
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("传入的ioc：" + applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String beanName) {
        log.info("当前bean的名字：" + beanName);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        String resolveStringValue = stringValueResolver.resolveStringValue("hello ${os.name}, i am #{20*11}");
        log.info("解析的字符串：" + resolveStringValue);
    }
}
