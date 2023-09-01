package com.service;

import com.entity.Product;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
//    后台商品展示
    PageInfo<Product> selectByPage(int pageNo, int pageSize, @Param("cid") int cid, @Param("name") String name, @Param("min")int min, @Param("max")int max);
    //前台数量展示
    List<Product> selectLimit(@Param("count") int count);
    //前台数量展示,热卖
    List<Product> selectPop(@Param("count") int count);
    //商品详情页
    Product selectById(int id);
    //导航展示某商品
    PageInfo<Product> selectCate(int pageNo,int pageSize,int cid);
    //改变商品状态，上下架
    void updateState(Product product);
    //删除商品
    void deleteById(int id);
    //删除多个商品
    void deleteMany(int[] ids);
    //后台添加商品
    void insertOne(Product product);
    //更新商品
    void update(Product product);
    //购买商品后更新数量和库存
    void updateC(Product product);
    //模糊查询
    PageInfo<Product> selectDim(int pageNo,int pageSize,String name);
    //    商品总数
    int selectCount();
}
