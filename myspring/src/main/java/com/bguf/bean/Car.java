package com.bguf.bean;

/**
 * @author gufb
 * @date 2021/8/20 2:12 PM
 */
public class Car {
    public Car() {
        System.out.println("car constructor");
    }

    public void init() {
        System.out.println("car......init......");
    }

    public void destroy() {
        System.out.println("car......destroy......");
    }
}
