package com.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.MailService;
import com.util.Mailutil;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//REST（Resource Representational State Transfer）表述性状态传递
@RestController
public class UserController {
    @Autowired
    private UserService us;
    @Autowired
    private MailService ms;
//分页查询用户
    @GetMapping("/user")
    public PageInfo<User> selectPage(@RequestParam(defaultValue = "1")int pageNo, @RequestParam(defaultValue = "4")int pageSize
    , @RequestParam(defaultValue = "-1")int state, String name, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date begin, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end){
        return us.selectByPage(pageNo,pageSize,state,name,begin,end);
    }
//    验证码
    @GetMapping("/handleYam")
    public void handleYzm(HttpServletResponse response, HttpSession session) throws IOException {
        //产生一张验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 40, 4, 60);
        String code=lineCaptcha.getCode();//获取验证码图片里面的真正验证码
        session.setAttribute("code",code);
        //把验证码图片放入到response里面交给浏览器
        ServletOutputStream outputStream=response.getOutputStream();
        lineCaptcha.write(outputStream);//把验证码图片放入到相应流里面
        response.flushBuffer();
    }
    //注册
    @GetMapping("/user/reg")
    public String reg(String email,String pwd,String pwd1,String number,HttpSession session){
        System.out.println(email);
        System.out.println(pwd);
        System.out.println(pwd1);
        boolean register = us.register(email, pwd, pwd1);
        String co=String.valueOf(session.getAttribute("code"));
        if(register&&co.equals(number)) {
            return "success";
        }else {
            return "error";
        }
    }
    //发送激活邮件
    @GetMapping("/mail")
    public String send(String email,HttpSession session){

        try {
            Mailutil.sendMail(email,ms.selectById(1));
            session.setAttribute("email",email);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    //邮件里面点击超链接，激活
    @PutMapping("/state")
    public String mail(HttpSession session){
        String email=(String) session.getAttribute("email");
        String status="";
        try {
            us.updateEmail(email,1);
            status="success";
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //登录
    @GetMapping("/user/login")
    public Map login(@RequestParam("email") String email,@RequestParam("password") String password,
                     @RequestParam(required = false)String fakeHead,@RequestParam(required = false)String nickName) {
        Map map=new HashMap();
        boolean zai = us.cunZai(email);
        User user = us.login(email,password);
        if(!zai){
            map.put("state",500);
        }
        else if(user==null){

            map.put("state",300);
        }else {
            if(user.getState()!=1){
                map.put("state",400);
            }else {
                String token = TokenUtil.sign((String) map.get("userName"));
                System.out.println(token);
                 if(nickName!=null&&user.getAvatar()==null){
                     User user1=new User(email,nickName,fakeHead);
                     us.updateQQ(user1);
                     user.setAvatar(fakeHead);
                     user.setNickName(nickName);
                 }
                map.put("user",user);
                map.put("state",200);
                map.put("token",token);
            }
        }
        return map;
    }
    //找回密码
    @GetMapping("/user/findPwd")
    public String findPwd(@RequestParam("email") String email){
        String status="";
        boolean exit = us.exit(email);
        if(exit){
            status="success";
        }
        return status;
    }
    //激活账号
    @GetMapping("/user/jiHuo")
    public String jiHuo(@RequestParam("email") String email){
        String status="";
        boolean exit = us.jiHuo(email);
        if(exit){
            status="success";
        }
        return status;
    }
//    根据id查询出来用户，用于后台管理员修改
    @GetMapping("/user/info")
    public User selectOne(@RequestParam(required = false)int id){
        return us.selectById(id);
    }

//    用户修改基本信息
    //1.上传头像
@PostMapping("/upload")
public Map<String,Object> upload(MultipartFile myImg) throws IOException {
    //把文件复制给d盘
    //得到上传时用户上传的文件名
    String fileName=myImg.getOriginalFilename();
    //给文件名重新赋值，避免两个文件名字相同被覆盖
    //fileName=System.currentTimeMillis()+fileName;
    fileName= UUID.randomUUID().toString()+fileName;
    System.out.println(fileName);
    File targetFile=new File("H:\\mobile\\src\\assets/avatar/"+fileName);
    //开始赋值
    myImg.transferTo(targetFile);

    //保存数据前给属性赋值
//        stu.setHeadImg(fileName);
    Map<String,Object> map=new HashMap<>();
    String newAvatar="http://localhost:8081/assets/avatar/"+fileName;
    map.put("avatarPath",newAvatar);
    return map;
}
//2.修改用户信息
    @PostMapping("/user/update")
    public Map<String,Object> update(@RequestBody User user){
        String status="";
        try {
            status="success";
            us.updateUser(user);
        } catch (Exception e) {
            status="error";
            e.getMessage();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("status",status);
        map.put("user",user);
        return map;
    }
//    2.后台添加用户
    @PostMapping("/user/add")
    public String add(@RequestBody User user){
        String status="";
        try {
            status="success";
            us.add(user);
        } catch (Exception e) {
            status="error";
            e.getMessage();
        }
        return status;
    }
    //    2.后台删除用户
    @DeleteMapping("/user")
    public String del(int id){
        String status="";
        try {
            status="success";
            us.deleteById(id);
        } catch (Exception e) {
            status="error";
            e.getMessage();
        }
        return status;
    }
    //后台批量删除
    @DeleteMapping("/user/many")
    public String dels(int[] ids){
        String status="";
        try {
            status="success";
            us.deleteMany(ids);
        } catch (Exception e) {
            status="error";
            e.getMessage();
        }
        return status;
    }
    //后台统计
    @GetMapping("/user/count")
    public int selcount(){
        return us.selectNum();
    }
    //用户改密
    @PutMapping("/user/pwd")
    public String updatePwd(@RequestBody User user){
        String status="";
        try {
            status="success";
            us.updatePwd(user);
        } catch (Exception e) {
            status="error";
            e.getMessage();
        }
        return status;
    }
}
