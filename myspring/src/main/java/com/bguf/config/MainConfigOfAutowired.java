package com.bguf.config;

import com.bguf.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *  spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 *      1. @Autowired
 *        a. 默认优先按照类型去容器中找对应的组件
 *        b. 如果找到多个相同类型的组件，再将属性的名称作为组件id去容器中查找
 *        c. 使用@Qualifier("bookDao2")明确指定需要装配的组件id
 *        d. 自动装配默认一定要将属性赋值好，没有就会报错，可以使用@Autowired(required=false)
 *        e. @Primary：让spring进行自动装配的时候，默认使用首选的bean
 *        f. 在e步骤中如果加上@Qualifier指定bean的id，最终就会确定为这个
 *      2. @Resource(JSR250)和@Inject(JSR330)[java规范的注解]
 *          @Resource ：可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的
 *              不支持@Primary功能，@Autowired(required=false)功能
 *          @Inject ：需要导入javax.inject依赖，和Autowired功能一样，但是没有属性可以选择
 *      AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能
 *      3. @Autowired可以放在构造器，参数，方法，属性上
 * @author gufb
 * @date 2021/8/23 10:03 AM
 */
@Configuration
@ComponentScan(value = {"com.bguf.service", "com.bguf.controller", "com.bguf.dao", "com.bguf.bean"})
public class MainConfigOfAutowired {
    @Primary
    @Bean(value = "bookDao2")
    public BookDao bookDao() {
//        BookDao bookDao = new BookDao();
//        bookDao.setLabel("2");
        return BookDao.builder()
                .label("2")
                .build();
//        return bookDao;
    }
}
