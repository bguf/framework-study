import com.bguf.bean.Person;
import com.bguf.config.MainConfigOfPropertyValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import sun.applet.Main;

/**
 * @author gufb
 * @date 2021/8/21 11:39 AM
 */
@Slf4j
public class MainTest_PropertyValue {
    AnnotationConfigApplicationContext configApplicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test1() {
        Person person = (Person) configApplicationContext.getBean("person");
       log.info("{}", person);
        ConfigurableEnvironment environment = configApplicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        log.info(property);
    }

    private void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        String[] definitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            log.info(name);
        }
    }
}
