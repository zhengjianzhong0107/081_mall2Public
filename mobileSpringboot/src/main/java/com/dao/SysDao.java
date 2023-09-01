package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Sys;
//让dao接口继承BaseMapper -- mybatis-plus可以自动生成增删改查的方法，不用我们自己写
public interface SysDao extends BaseMapper<Sys> {

}
