import com.github.pagehelper.PageInfo;
import com.dao.UserDao;
import com.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.UserService;

import java.util.List;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private UserDao usd;
    @Autowired
    private UserService us;

    @Test
    public void sel() {
        us = (UserService) ac.getBean("userServiceImpl");
        PageInfo<User> userPageInfo = us.selectByPage(1, 4,1,null,null,null);
        List<User> list = userPageInfo.getList();
        for (User u : list
        ) {
            System.out.println(u);
        }
    }

    @Test
    public void login() {
        us = (UserService) ac.getBean("userServiceImpl");
        User login = us.login("123456@qq.com", "123456");
        System.out.println(login);
    }

    @Test
    public void selectOne() {
        us = (UserService) ac.getBean("userServiceImpl");
        User user = us.selectById(2);
        System.out.println(user);
    }

    //用户更新信息
    @Test
    public void update() {
        us = (UserService) ac.getBean("userServiceImpl");
        try {
            us.updateUser(new User(2, "李儒去", 24, "男", "13596874659", "12.jpg"));
            System.out.println("更新成功");
        } catch (Exception e) {
            System.out.println("更新失败");
            e.printStackTrace();
        }
    }

    //注册
    @Test
    public void reg() {
        us = (UserService) ac.getBean("userServiceImpl");
        boolean register = us.register("1234587@qq.com", "123456", "123456");
        if (register) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }
    //后台统计有效人数
    @Test
    public void count(){
        us = (UserService) ac.getBean("userServiceImpl");

        System.out.println(us.selectNum());
    }
}
