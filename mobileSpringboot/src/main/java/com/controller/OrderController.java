package com.controller;

import com.entity.Cart;
import com.entity.Order;
import com.entity.OrderDetails;
import com.entity.Product;
import com.github.pagehelper.PageInfo;
import com.service.ProductService;
import com.util.OrderIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.CartService;
import com.service.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService os;
    @Autowired
    private CartService cs;
    @Autowired
    private ProductService ps;

//添加订单
    @PostMapping("/order")
    public Map<String, Object> insert(@RequestBody Order order) {
        System.out.println(order);
        String status = "";
        List<Cart> carts = cs.selectByUserId(order.getUserId());
        int count = 0;
        double totalPrice = 0;
        System.out.println(1111111111);
        List<OrderDetails> orderDetails = new ArrayList<>();
        //往集合里面放入数据
        for (Cart c : carts) {
            OrderDetails od = new OrderDetails();
            od.setCount(c.getCount());
            od.setPid(c.getPid());
            od.setPrice(c.getProduct().getPrice());
            orderDetails.add(od);
            count += c.getCount();
            totalPrice += c.getTotalPrice();
        }
        System.out.println(222222);
        order.setGoodPrice(totalPrice);
        order.setOrderDetails(orderDetails);
        //获取订单id
        String id= OrderIdUtil.getId();
        order.setId(id);
        os.insertOne(order);
        System.out.println(id);
        if(order.getId()!=null){
           status="success";
            cs.deleteByUid(order.getUserId());
        }
        System.out.println(222222222);
      Map<String , Object> map = new HashMap<>();
         map.put("id",id);
         map.put("status",status);
        return map;
    }
    //我的订单展示
    @GetMapping("/order")
    public PageInfo<Order> sel(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "6")int pageSize, int uid){
        return os.selectOrder(pageNo, pageSize, uid);
    }
    //修改订单状态
    @GetMapping("/order/state")
    public void update(String out_trade_no, HttpServletResponse resp){
        String status="";
        try {
            os.updateState(out_trade_no,1);
            status="success";
            //支付成功修改库存和销量
            Order order = os.selectByOid(out_trade_no);
            List<OrderDetails> orderDetails = order.getOrderDetails();
            for (OrderDetails o:orderDetails
                 ) {
                Product product = ps.selectById(o.getProduct().getId());
                product.setSaleNum(product.getSaleNum()+o.getCount());
                product.setInventory(product.getInventory()-o.getCount());
               ps.updateC(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if(status.equals("success")){
//
//            try {
//                resp.sendRedirect("http://localhost:8081/#/pay_ok");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
    //修改订单状态(发货收货退款)

    @GetMapping("/order/status")
    public String status(String id,int state){
        String status="";
        try {
            status="success";
            os.updateState(id, state);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping("/order/user")
    public PageInfo<Order>sel(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "6")int pageSize,String userName,@RequestParam(defaultValue = "0")String id,@RequestParam(defaultValue = "-1")int state){
        return os.selectByPage(pageNo, pageSize,userName,id,state);
    }
    //用户退款，已完成
    @GetMapping("/order/shouHou")
    public PageInfo<Order>seLu(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "6")int pageSize,String userName,@RequestParam(defaultValue = "0")String id,@RequestParam(defaultValue = "-1")int state){
        return os.selectByUserPage(pageNo, pageSize,userName,id,state);
    }
    @GetMapping("/order/detail")
    public Map<String ,Object> seld(String id){
        Map<String ,Object>map=new HashMap<>();
        //商品详情
        Order order = os.selectByOid(id);
        //收货地址详情
        Order order1 = os.selectByOid1(id);
        map.put("order",order);
        map.put("order1",order1);
        return map;
    }
    //订单数量查询
    @GetMapping("/order/count")
    public int selectCount(@RequestParam(defaultValue = "0")String id){
        return os.selectCount(id);
    }
    //删除订单
    @DeleteMapping("/order")
    public String delete(String id){
        String status="";
        try {
            status="success";
            os.del(id);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //删除多个订单
    @DeleteMapping("/order/many")
    public String deletes(String[] ids){
        String status="";
        try {
            status="success";
            os.dels(ids);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
}
