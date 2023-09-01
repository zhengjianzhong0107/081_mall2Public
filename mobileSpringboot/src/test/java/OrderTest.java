import com.github.pagehelper.PageInfo;
import com.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private OrderService os;

    @Test
    public void sel() {
        os = (OrderService) ac.getBean("orderServiceImpl");
        PageInfo<Order> orderPageInfo = os.selectByPage(1, 5, null, "1", 0);
        List<Order> list = orderPageInfo.getList();
        list.forEach(System.out::println);
    }

    //订单号测试
    @Test
    public void id() {
        Date now = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        long x=Long.parseLong(f.format(now));
        System.out.println(x);
    }
    @Test
    public void xq(){
        os.selectByOid("20210411152615");
    }
}
