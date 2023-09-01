package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.ProductDao;
import com.entity.Product;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao pd;
    //分页查询商品,用于后台管理
    @Override
    public PageInfo<Product> selectByPage(int pageNo, int pageSize, int cid, String name, int min, int max) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(pd.selectAll(cid,name,min,max));
    }

    @Override
    public List<Product> selectLimit(int count) {
        return pd.selectLimit(count);
    }

    @Override
    public List<Product> selectPop(int count) {
        return pd.selectPop(count);
    }

    //商品详情
    @Override
    public Product selectById(int id) {
        return pd.selectById(id);
    }
//前台点击导航，分类展示商品
    @Override
    public PageInfo<Product> selectCate(int pageNo,int pageSize,int cid) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(pd.selectCate(cid));
    }
//后台商品的上下架
    @Override
    public void updateState(Product product) {
        pd.updateState(product);
    }
//删除商品
    @Override
    public void deleteById(int id) {
        pd.deleteById(id);
    }
//批量删除
    @Override
    public void deleteMany(int[] ids) {
        for (int id:ids
             ) {
            pd.deleteById(id);
        }
    }
//添加商品
    @Override
    public void insertOne(Product product) {
        pd.insertOne(product);
    }
//更新商品
    @Override
    public void update(Product product) {
        pd.update(product);
    }
//更新销量和库存
    @Override
    public void updateC(Product product) {
        pd.updateC(product);
    }


    //搜索商品
    @Override
    public PageInfo<Product> selectDim(int pageNo,int pageSize,String name) {
        PageHelper.startPage(pageNo,pageSize);
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        if(name!=null){
            queryWrapper.like("product_name", name).or().like("messages", name).or().like("color",name);
        }

        return new PageInfo<>(pd.selectList(queryWrapper));
    }

    @Override
    public int selectCount() {
        return pd.selectCount();
    }

}
