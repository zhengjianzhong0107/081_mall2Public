package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Cart;

import java.util.List;

public interface CartDao extends BaseMapper<Cart> {
    //更具用户id查询购物车
    List<Cart> selectByUserId(int uid);
    //更具商品ID查询购物车
    Cart selectByPid(int pid);
    //更改数量
    void updateByCountId(Cart cart);
//    清空购物车
    void deleteByUid(int uid);
}
