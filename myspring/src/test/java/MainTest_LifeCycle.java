import com.bguf.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author gufb
 * @date 2021/8/20 2:17 PM
 */
public class MainTest_LifeCycle {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);

    @Test
    public void test1() {
        context.close();
    }
}
