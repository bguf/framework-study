import com.bguf.tx.CoursesService;
import com.bguf.tx.TxConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gufb
 * @date 2021/8/25 10:09 AM
 */
public class MainTest_tx {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);

    @Test
    public void test1() {
        CoursesService coursesService = context.getBean(CoursesService.class);
        coursesService.insertCourse();
    }
}
