package com.service;

import com.entity.Order;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
//    添加订单
    void insertOne(Order order);
//    用户中心查看订单
PageInfo<Order> selectOrder(int pageNo,int PageSize,int userId);
    //修改订单状态
    void updateState(@Param("id") String id, @Param("orderState") int orderState);
    //后台管理员查看订单
    PageInfo<Order> selectByPage(int pageNo,int PageSize,@Param("userName")String userName,@Param("id")String id,@Param("state")int state);
    //用户售后，退款
    PageInfo<Order> selectByUserPage(int pageNo,int PageSize,@Param("userName")String userName,@Param("id")String id,@Param("state")int state);
    //后台订单详情
    Order selectByOid(String id);
    Order selectByOid1(String id);
    //    后台查看订单数量
    int selectCount(String id);
    //删除订单
    void del(String id);
    void dels(String[] ids);
    //商品是否被购买
    boolean selectIfBuy(@Param("id")int id,@Param("pid")int pid);
}
