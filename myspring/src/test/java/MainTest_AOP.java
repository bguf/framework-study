import com.bguf.aop.MathCalculator;
import com.bguf.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gufb
 * @date 2021/8/24 11:59 AM
 */
public class MainTest_AOP {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

    @Test
    public void test1() {
        // aop不要用自己创建的对象
//        MathCalculator mathCalculator = new MathCalculator();
        MathCalculator mathCalculator = context.getBean(MathCalculator.class);
        mathCalculator.div(3, 0);
    }
}
