import com.bguf.config.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author gufb
 * @date 2021/8/17 5:42 PM
 */
@Slf4j
public class MainTest {
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

    }
}
