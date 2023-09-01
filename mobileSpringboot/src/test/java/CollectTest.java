import com.entity.Collect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.CollectService;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class CollectTest {
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private CollectService cs;
    //判断是否被收藏
    @Test
    public void ifCol(){
         cs = (CollectService)ac.getBean("collectServiceImpl");
        boolean sel = cs.sel(new Collect(1, 7));
        System.out.println(sel);
    }
}
