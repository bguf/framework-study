package com.bguf.config;

import com.bguf.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期：创建--初始化--销毁
 * 容器管理bean的生命周期
 * 可以自定义初始化和销毁方法，容器在bean进行到当前生命周期是自动调用自定义的初始化和销毁方法
 *      1. 指定初始化和销毁方法：
 *          指定init-method和destroy-method
 * @author gufb
 * @date 2021/8/20 2:07 PM
 */
@Configuration
public class MainConfigOfLifeCycle {
    @Bean
    public Car car() {
        return new Car();
    }
}
