package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MailService;
import com.util.MailPwdUtil;
import com.util.Mailutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.UserService;

import java.util.Date;
import java.util.List;

@Service //替换bean标签，创建对象
public class UserServiceImpl implements UserService {
    @Autowired //自动注入，无需再写get和set
    private UserDao ud;
    @Autowired
    private MailService ms;
//分页查询所有用户
    @Override
    public PageInfo<User> selectByPage(int pageNo, int pageSize, int state, String name, Date begin, Date end) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> users = ud.selectAll(state, name, begin, end);
        return new PageInfo<User>(users);
    }
//用户注册
    @Override
    public boolean register(String email,String pwd,String pwd1) {
        User user1 = ud.selectByName(email);
        if(user1==null||pwd.equals(pwd1)){
            ud.insertOne(email,pwd);
            return true;
        }
        return false;
    }
//用户登录
    @Override
    public User login(String email, String password) {
        return  ud.selectByNameAndPwd(email, password);


    }
//根据ID查询用户
    @Override
    public User selectById(int id) {
        return ud.selectById(id);
    }
//更新用户信息
    @Override
    public void update(User user) {
     ud.updateById(user);
    }

    @Override
    public void updateUser(User user) {
        ud.updateU(user);
    }

    //更新用户状态，注册激活
    @Override
    public void updateEmail(String email, int state) {
ud.updateState(email,state);
    }

    @Override
    public void add(User user) {
        ud.add(user);
    }
//删除
    @Override
    public void deleteById(int id) {
        ud.deleteById(id);
    }
//批量删除
    @Override
    public void deleteMany(int[] ids) {
        for (int id:ids
             ) {
            ud.deleteById(id);
        }
    }

    @Override
    public int selectNum() {
        return ud.selectCount();
    }

    @Override
    public void updatePwd(User user) {
        ud.updatePwd(user);
    }
//找回密码
    @Override
    public boolean exit(String email) {
        User user = ud.selectByName(email);
        if(user!=null){
            MailPwdUtil.sendMail(email,ms.selectById(1),user.getPwd());
            return true;
        }
        return false;
    }
    //激活账号
    @Override
    public boolean jiHuo(String email) {
        User user = ud.selectByName(email);
        if(user!=null){
            Mailutil.sendMail(email,ms.selectById(1));
            return true;
        }
        return false;
    }

    @Override
    public boolean cunZai(String email) {
        User user = ud.selectByName(email);
        if(user!=null){
            return true;
        }
        return false;
    }

    @Override
    public void updateQQ(User user) {
        ud.updateQQ(user);
    }
}
