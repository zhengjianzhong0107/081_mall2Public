package com.dao;

import com.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    void insert(Order order);
    List<Order> selectOrder(int userId);
    void updateState(@Param("id") String id,@Param("orderState") int orderState);
    //后台管理员查看订单
    List<Order> selectHtOrder(@Param("userName")String userName,@Param("id")String id,@Param("state")int state);
    //用户查看退款，已完成
    List<Order> selectUserOrder(@Param("userName")String userName,@Param("id")String id,@Param("state")int state);
    //管理员查看订单详情
    Order selectByOid(String id);
    Order selectByOid1(String id);
//    后台查看订单数量
    int selectCount(@Param("id") String id);
    //删除订单
    void del(String id);
    //商品是否被购买
    Order selectIfCom(@Param("id")int id,@Param("pid")int pid);
    //退款、售后
}
