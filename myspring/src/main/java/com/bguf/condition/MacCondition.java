package com.bguf.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author gufb
 * @date 2021/8/18 3:46 PM
 */
public class MacCondition implements Condition {
    /**
     *
     * @param conditionContext：判断条件能使用的上下文（环境）
     * @param annotatedTypeMetadata：注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, 
                           AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 1. 获取到ioc使用的bean factory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        // 2. 获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        // 3. 获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        // 4. 获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        // 可以判断容器中的bean注册情况，也可以给容器中注册bean
        boolean res = registry.containsBeanDefinition("person");
        
        String osName = environment.getProperty("os.name");
        if (osName.equals("Mac OS X")) {
            return true;
        }
        return false;
    }
}
