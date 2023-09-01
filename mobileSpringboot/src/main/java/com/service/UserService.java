package com.service;

import com.entity.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserService {
//    分页查询用户信息
    PageInfo<User>selectByPage(@Param("pageNo")int pageNo, @Param("pageSize")int pageSize, @Param("state") int state, @Param("name")String name, @Param("begin") Date begin, @Param("end")Date end);
    //    注册用户
    boolean register(@Param("email")String email,@Param("pwd")String pwd,@Param("pwd1")String pwd1);
//    用户登录
    User login(@Param("email") String email,@Param("password") String password);

    //    根据ID找指定用户
    User selectById(@Param("id") int id);
    //    更新用户信息
    void update(User user);
    //用户信息更新
    void updateUser(User user);
    //    更新状态
    void updateEmail(String email,int state);
    //后台添加用户
    void add(User user);
    //删除未激活和未绑定信息用户
    void deleteById(int id);
    //批量删除
    void deleteMany(int[] ids);
    //后台有效用户统计
    int selectNum();
//用户改密
void updatePwd(User user);
//判断用户是否存在，用于找回密码
    boolean exit(String email);
    //判断用户是否存在，用于激活
    boolean jiHuo(String email);
    //判断用户是否存在
    boolean cunZai(String email);
    //更新QQ用户
    void updateQQ(User user);
}
