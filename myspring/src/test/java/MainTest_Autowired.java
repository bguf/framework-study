import com.bguf.config.MainConfigOfAutowired;
import com.bguf.dao.BookDao;
import com.bguf.service.BookService;
import com.bguf.service.impl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gufb
 * @date 2021/8/23 10:10 AM
 */
@Slf4j
public class MainTest_Autowired {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

    @Test
    public void test1() {
        BookService bean = context.getBean(BookServiceImpl.class);
        bean.print();
//        BookDao bean1 = context.getBean(BookDao.class);
//        log.info("{}", bean1);
    }
}
