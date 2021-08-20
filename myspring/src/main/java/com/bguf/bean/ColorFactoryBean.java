package com.bguf.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个spring定义的工厂bean
 * @author gufb
 * @date 2021/8/18 5:09 PM
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回一个Color对象，这个对象会添加到容器中
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
