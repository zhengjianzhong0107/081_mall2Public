import com.entity.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.MailService;
import com.util.Mailutil;

//开启Spring对Junit的支持
@RunWith(SpringJUnit4ClassRunner.class)
//设置配置文件的路径
@ContextConfiguration("classpath:applicationContext.xml")
public class MailTest {
    //自动从工厂中获取对象，为属性赋值
    @Autowired
    private ApplicationContext context;
    @Autowired
    private MailService ms;

    @Test
    public void sel() {
        Mail mail=ms.selectById(1);
        Mailutil.sendMail("3614930772@qq.com",mail);
    }
    @Test
    public void selectById(){
        System.out.println(ms.selectById(1).getEmail());
        System.out.println(ms.selectById(1).getHost());
        System.out.println(ms.selectById(1).getPort());
        System.out.println(ms.selectById(1).getPwd());
    }
}
