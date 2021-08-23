package com.bguf.config;

import com.bguf.bean.Color;
import com.bguf.bean.ColorFactoryBean;
import com.bguf.bean.Person;
import com.bguf.bean.Red;
import com.bguf.condition.MacCondition;
import com.bguf.condition.MyImportBeanDefinitionRegistrar;
import com.bguf.condition.MyImportSelector;
import com.bguf.condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * @author gufb
 * @date 2021/8/18 3:02 PM
 */
@Configuration
/**
 * import快速导入组件
 */
@Import(value = {Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {
    /**
     * 默认是单实例
     * scope取值：
     * 1. prototype：多实例，ioc容器启动并不会去调用方法创建对象放在容器中
     *                      每次获取的时候才会调用方法创建对象
     * 2. singleton：单实例（默认值），ioc容器启动会调用方法创建对象，放到ioc容器中
     *                      以后每次获取，从容器中拿(map.get())
     * 下面这两个在web环境下使用，不过基本不会用
     * 3. request：同一次请求创建一个实例
     * 4. session：同一个session创建一个实例
     * @return
     */
     @Scope(value = "singleton")
    /**
     * 懒加载：
     *      单实例bean，默认在容器启动的时候创建对象
     *      懒加载：容器启动不创建对象，第一次使用（获取）bean的时候创建对象，并进行初始化
     */
    @Lazy
    @Bean(value = "person")
    public Person person() {
        System.out.println("-----给容器添加Person");
         return new Person();
    }

    /**
     * @Conditional：
     *      按照一定的条件进行判断，满足条件给容器注册bean
     *  如果是windows系统，给容器注册aaa这个bean
     *  如果是mac系统，给容器注册bbb这个bean
     */
    @Conditional(value = {WindowsCondition.class})
    @Bean(value = "aaa")
    public Person person01() {
        return new Person();
    }

    @Conditional(value = {MacCondition.class})
    @Bean(value = "bbb")
    public Person person02() {
        return new Person();
    }

    /**
     * 给容器中注册组件：
     * 1. 包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2. @Bean [导入的第三方包里面的组件]
     * 3. @Import [快速给容器中导入一个组件]
     *      a. @Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     *      b. @ImportSelector：返回需要导入组件的全类名数组
     *      c. ImportBeanDefinitionRegistrar：手动注册bean到容器中
     *  4. 使用spring提供的FactoryBean（工厂Bean）
     *      a. 默认获取到的是工厂bean调用getObject创建的对象
     *      b. 要获取工厂bean本身，需要给id前加&标识：&colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

}
