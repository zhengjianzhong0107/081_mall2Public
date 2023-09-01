package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//让dao接口继承BaseMapper -- mybatis-plus可以自动生成增删改查的方法，不用我们自己写
public interface UserDao  extends BaseMapper<User> {
    //通过名字查询是否存在同名用户，如有则注册失败
    User selectByName(String name);
    //注册
    void insertOne(@Param("email") String email,@Param("pwd") String pwd);
    //通过密码和用户名查询用户是否满足条件用于登录
    User selectByNameAndPwd(@Param("email") String email, @Param("password") String password);
    //更新用户
    void updateU(User user);
    //用户激活，更改状态
    void updateState(@Param("email") String email,@Param("state") int state);
    //后台添加用户
    void add(User user);
    //后台有效用户统计
   int selectCount();
   //后台查询所有
    List<User> selectAll(@Param("state") int state, @Param("name")String name, @Param("begin")Date begin,@Param("end")Date end);
 //    用户改密码
   void updatePwd(User user);
   //更新QQ用户
    void updateQQ(User user);
}
