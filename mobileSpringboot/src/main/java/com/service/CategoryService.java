package com.service;

import com.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    //    查询所有类别信息分类商品下的数量
    PageInfo<Category>selectByPage(int pageNo, int pageSize);
    List<Category> selectAll();
    //分类商品下的数量
    List<Category> selectAc();
    //后台添加类别
    void insert(Category cate);
    //后台更新类别
    void update(Category cate);
    //后台删除类别
    void delete(int id);
    //前台根据id查询类别
    Category selectById(int id);
    //    分类总数
   int selectCount();
}
