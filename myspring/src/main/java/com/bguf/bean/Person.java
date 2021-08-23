package com.bguf.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author gufb
 * @date 2021/8/17 5:40 PM
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    /**
     * 使用@Value赋值
     * 1. 基本数值
     * 2. 可以写SpEL：#{}
     * 3. 可以写${}：取出配置文件中的值（在运行环境变量里面的值）
     */
    @Value("张三")
    private String name;
    @Value("#{20+2}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;
}
