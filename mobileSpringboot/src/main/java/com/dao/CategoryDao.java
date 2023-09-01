package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Category;

import java.util.List;

//让dao接口继承BaseMapper -- mybatis-plus可以自动生成增删改查的方法，不用我们自己写
public interface CategoryDao extends BaseMapper<Category> {
    List<Category> selectAll();
//分类商品下的数量
    List<Category> selectAc();
    //    分类总数
    int selectCount();
}
