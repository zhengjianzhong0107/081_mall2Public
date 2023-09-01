package com.service;

import com.entity.Cart;

import java.util.List;

public interface CartService {
    //根据用户id查询
    //更具用户id查询购物车
    List<Cart> selectByUserId(int uid);
//    添加购物车
    void insertOne(Cart cart);
    //更具商品ID查询购物车
    Cart selectByPid(int pid);
    //更新购物车的商品个数
    void updateOne(Cart cart);
    //删除一个购物车里的商品
    void deleteById(int id);
    //删除多个
    void deleteMany(int[] ids);
    //更改数量
    void updateByCountId(Cart cart);
    //    清空购物车
    void deleteByUid(int uid);
}
