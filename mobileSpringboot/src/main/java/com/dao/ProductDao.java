package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao extends BaseMapper<Product> {
    //根据所属分类cid查到商品
    //展示所有，后台用于分页查询
    List<Product> selectAll(@Param("cid") int cid,@Param("name") String name,@Param("min")int min,@Param("max")int max);
    //前台用于分类展示
    List<Product> selectCate(@Param("cid") int cid);
    //最新商品和最热卖商品
    List<Product> selectLimit(@Param("count") int count);
    List<Product> selectPop(@Param("count") int count);
    //改变商品状态，上下架
    void updateState(Product product);
    //后台添加商品
    void insertOne(Product product);
    //更新商品
    void update(Product product);
    //购买商品后更新数量和库存
    void updateC(Product product);
    void updateR(Product product);
//    商品总数
    int selectCount();
}
