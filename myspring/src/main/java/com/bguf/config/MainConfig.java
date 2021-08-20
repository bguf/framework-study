package com.bguf.config;

import com.bguf.bean.Person;
import com.bguf.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author gufb
 * @date 2021/8/17 5:43 PM
 */
@Configuration
@ComponentScans(
        value = {@ComponentScan(
                value = "com.bguf",
                includeFilters = {
                // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
                        },
                useDefaultFilters = false)
        }
)
public class MainConfig {
    @Bean(value = "person")
    public Person person() {
        return new Person("lisi", 20);
    }
}
