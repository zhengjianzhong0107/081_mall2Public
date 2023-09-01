package com.service.impl;

import com.dao.CategoryDao;
import com.entity.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //替换bean标签，创建对象
public class CategoryServiceImpl implements CategoryService {
    @Autowired //自动注入，无需再写get和set
    private CategoryDao cd;

    @Override
    public PageInfo<Category> selectByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(cd.selectAc());
    }
    //查询所有类别
    @Override
    public List<Category> selectAll() {
        return cd.selectAll();
    }
//    和分类下商品数量
    @Override
    public List<Category> selectAc() {
        return cd.selectAc();
    }

    //添加类别
    @Override
    public void insert(Category cate) {
        cd.insert(cate);
    }

    //修改类别
    @Override
    public void update(Category cate) {
        cd.updateById(cate);
    }

    //删除类别
    @Override
    public void delete(int id) {
        cd.deleteById(id);
    }
//根据ID查询类别
    @Override
    public Category selectById(int id) {
        return cd.selectById(id);
    }

    @Override
    public int selectCount() {
        return cd.selectCount();
    }


}
