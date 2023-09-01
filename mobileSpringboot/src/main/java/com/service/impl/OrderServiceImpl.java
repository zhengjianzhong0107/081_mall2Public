package com.service.impl;

import com.dao.OrderDao;
import com.dao.OrderDetailsDao;
import com.entity.Order;
import com.entity.OrderDetails;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao od;
    @Autowired
    private OrderDetailsDao odd;
    @Override
    public void insertOne(Order order) {
        od.insert(order);//把订单插入到数据库里面
        List<OrderDetails> orderDetails=order.getOrderDetails();
        //获取到订单下面的所有订单详情信息
        for(OrderDetails ods:orderDetails){
            ods.setOrderId(order.getId());
            odd.insert(ods);
        }
    }
//查询用户订单
    @Override
    public PageInfo<Order> selectOrder(int pageNo,int pageSize,int userId) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(od.selectOrder(userId));
    }
    //更新订单状态
    @Override
    public void updateState(String id, int orderState) {
        od.updateState(id,orderState);
    }

    @Override
    public PageInfo<Order> selectByPage(int pageNo, int PageSize,String userName,String id,int state) {
        PageHelper.startPage(pageNo, PageSize);
        return new PageInfo<>(od.selectHtOrder(userName,id,state));
    }
//用户退款，售后，已完成
    @Override
    public PageInfo<Order> selectByUserPage(int pageNo, int PageSize, String userName, String id, int state) {
        PageHelper.startPage(pageNo, PageSize);
        return new PageInfo<>(od.selectUserOrder(userName,id,state));
    }

    //后台订单详情
    @Override
    public Order selectByOid(String id) {
        return od.selectByOid(id);
    }

    @Override
    public Order selectByOid1(String id) {
        return od.selectByOid1(id);
    }
//订单数量
    @Override
    public int selectCount(String id) {
        return od.selectCount(id);
    }
//管理员删除订单
    @Override
    public void del(String id) {
        odd.deleteId(id);

        if(odd.selectOne(id)==null){

            od.del(id);
        }
    }
    //管理员删除订单
    @Override
    public void dels(String[] ids) {
        for (String id:ids
             ) {
            odd.deleteId(id);

            if(odd.selectOne(id)==null){
                od.del(id);
            }
        }
    }

    @Override
    public boolean selectIfBuy(int id, int pid) {
        Order order = od.selectIfCom(id, pid);
        return order != null;
    }


}
