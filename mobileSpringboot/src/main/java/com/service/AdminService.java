package com.service;

import com.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService {

    boolean login(@Param("name")String name,@Param("pwd")String pwd);
    Admin selectByNameAndPwd(@Param("name")String name, @Param("pwd")String pwd);
    //更具id查询
    Admin selectById(int id);
    void updateById(Admin admin);
}
