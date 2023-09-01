import com.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.CommentService;

//直接使用JUnit测试Spring项目，需要手动编码获取context工厂，需要手动从工厂中获取对象，测试繁
//        琐。
//        解决方案：使用Spring-test，简化了Spring项目的测试。
//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class CommentTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private CommentService cs;

    @Test
    public void sel(){
        cs = (CommentService)ac.getBean("commentServiceImpl");
        boolean b = cs.exitCom(19, 1);
        System.out.println(b);
//        PageInfo<Comment> commentPageInfo = cs.selectByPage(1, 12, "");
//        List<Comment> list = commentPageInfo.getList();
//        list.forEach(System.out::println);

    }
    @Test
    public void in(){
        cs = (CommentService)ac.getBean("commentServiceImpl");
        try {
            cs.insertO(new Comment("hao",5,1,40));
            System.out.println("添加成");
        } catch (Exception e) {
            System.out.println("添加失败");
            e.printStackTrace();
        }
    }
}
