import com.github.pagehelper.PageInfo;
import com.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.ProductService;

import java.util.List;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private ProductService ps;
    //分页查询所有+条件查询、模糊查询

    @Test
    public void sel(){
        ps=(ProductService)ac.getBean("productServiceImpl");
        PageInfo<Product> productPageInfo = ps.selectByPage(1, 5, 0,"221",0,0);
        List<Product> list = productPageInfo.getList();
        list.forEach(System.out::println);
    }
    //限制条数
    @Test
    public void selectLimit(){
        ps=(ProductService)ac.getBean("productServiceImpl");
        List<Product> products = ps.selectLimit(2);
        products.forEach(System.out::println);
    }
    //商品详情
    @Test
    public void selectOne(){
        ps=(ProductService)ac.getBean("productServiceImpl");
        Product product = ps.selectById(4);
        System.out.println(product);
    }
    //商品详情
    @Test
    public void selectCate(){
        ps=(ProductService)ac.getBean("productServiceImpl");
        PageInfo<Product> products = ps.selectCate(1,10,2);
        products.getList().forEach(System.out::println);
    }
    //状态改变，上下架
    @Test
    public void selectSate(){
        ps=(ProductService)ac.getBean("productServiceImpl");
       ps.updateState(new Product(1,0));
    }
}
