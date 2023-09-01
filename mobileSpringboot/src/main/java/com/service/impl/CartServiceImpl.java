package com.service.impl;

import com.dao.CartDao;
import com.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.CartService;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cd;
    @Override
    public List<Cart> selectByUserId(int uid) {
        return cd.selectByUserId(uid);
    }
//添加购物车
    @Override
    public void insertOne(Cart cart) {
        cd.insert(cart);
    }

    @Override
    public Cart selectByPid(int pid) {
        return cd.selectByPid(pid);
    }

    //更新购物车
    @Override
    public void updateOne(Cart cart) {
        cd.updateById(cart);
    }

    @Override
    public void deleteById(int id) {
        cd.deleteById(id);
    }

    @Override
    public void deleteMany(int[] ids) {
        for (int i=0;i<ids.length;i++) {
            cd.deleteById(ids[i]);
        }
    }

    @Override
    public void updateByCountId(Cart cart) {
        cd.updateByCountId(cart);
    }

    @Override
    public void deleteByUid(int uid) {
        cd.deleteByUid(uid);
    }


}
