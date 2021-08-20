package com.bguf.condition;

import com.bguf.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gufb
 * @date 2021/8/18 4:36 PM
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 把所有需要添加到容器中的bean，
     * 可以使用BeanDefinitionRegistrar.registerBeanDefinition手动注册
     * @param importingClassMetadata：当前类的注解信息
     * @param registry：bean定义的注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.bguf.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.bguf.bean.Blue");
        if (red && blue) {
            // 指定bean定义信息，（Bean的类型）
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册一个Bean，指定bean名
            registry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }
    }
}
