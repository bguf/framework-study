package com.bguf.config;

import com.bguf.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author gufb
 * @date 2021/8/21 11:38 AM
 */
@Configuration
@PropertySource(value = {"classpath:/person.properties"})
public class MainConfigOfPropertyValues {
    @Bean
    public Person person() {
        return new Person();
    }
}
