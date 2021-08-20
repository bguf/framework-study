import com.bguf.bean.Blue;
import com.bguf.bean.Person;
import com.bguf.config.MainConfig;
import com.bguf.config.MainConfig2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Map;

/**
 * @author gufb
 * @date 2021/8/17 5:42 PM
 */
@Slf4j
public class MainTest {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        System.out.println(context.getBean(Person.class));
//        System.out.println(Arrays.toString(context.getBeanNamesForType(Person.class)));
        for (String beanName : context.getBeanDefinitionNames()) {
            log.debug(beanName);
        }
    }

    @Test
    public void test2() {
        for (String beanName : context.getBeanDefinitionNames()) {
            log.debug(beanName);
        }
        log.error("ioc容器创建完成。。。");
        Person person1 = (Person) context.getBean("person");
        Person person2 = (Person) context.getBean("person");
        System.out.println(person1 == person2);
    }

    @Test
    public void test3() {
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String beanName : beanNamesForType) {
            log.debug(beanName);
        }
        Map<String, Person> personMap = context.getBeansOfType(Person.class);
        System.out.println(personMap);
    }

    @Test
    public void test4() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            log.debug("{}", beanName);
        }
    }

    @Test
    public void test5() {
        System.out.println(context.getBean("&colorFactoryBean").getClass());
    }
}
