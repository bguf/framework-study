package com.bguf.config;

import com.bguf.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期：创建--初始化--销毁
 * 容器管理bean的生命周期
 * 可以自定义初始化和销毁方法，容器在bean进行到当前生命周期是自动调用自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 *      对象创建完成，并赋值好，调用初始化方法
 *  BeanPostProcessor.postProcessAfterInitialization
 *
 * 销毁：
 *      单实例bean：容器关闭的时候
 *      多实例bean：容器不会管理bean，不会调用销毁方法
 *
 *      1. 指定初始化和销毁方法：
 *          通过@Bean 指定init-method和destroy-method
 *      2. 通过让Bean实现InitializingBean（定义初始化逻辑）
 *          实现DisposableBean（定义销毁逻辑）
 *      3. 使用jsr250注解：
 *          @PostConstruct: 在bean创建完成，并且属性赋值完成，来执行初始化方法
 *          @PreDestroy: 在容器销毁bean之前通知进行清理工作
 *      4. BeanPostProcessor[interface]：bean后置处理器
 *          在bean初始化前后进行一些处理工作
 *          postProcessBeforeInitialization：初始化之前进行处理工作
 *          postProcessAfterInitialization：初始化之后进行处理工作
 * @author gufb
 * @date 2021/8/20 2:07 PM
 */
@Configuration
@ComponentScan(value = "com.bguf.bean")
public class MainConfigOfLifeCycle {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
