package com.bguf.config;

import com.bguf.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author gufb
 * @date 2021/8/17 5:43 PM
 */
@Configuration
@ComponentScan(value = "com.bguf", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes ={Controller.class})
})
public class MainConfig {
    @Bean(value = "person")
    public Person person() {
        return new Person("lisi", 20);
    }
}
