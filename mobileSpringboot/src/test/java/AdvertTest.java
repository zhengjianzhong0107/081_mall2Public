import com.entity.Advert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.AdvertService;

import java.util.List;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class AdvertTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private AdvertService as;
    @Test
    public void sel(){
        as= (AdvertService) ac.getBean("advertServiceImpl");
        List<Advert> lb = as.selectGg("tz");
        for (Advert l:lb
             ) {
            System.out.println(l);
        }
    }
    @Test
    public void selOne(){
        as= (AdvertService) ac.getBean("advertServiceImpl");
        Advert gg = as.selectOne(5);
        System.out.println(gg);

    }
}
