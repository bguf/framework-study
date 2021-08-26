package com.bguf.bean;

/**
 * @author gufb
 * @date 2021/8/18 4:06 PM
 */
public class Color {
    private Car car;

    public Color() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
