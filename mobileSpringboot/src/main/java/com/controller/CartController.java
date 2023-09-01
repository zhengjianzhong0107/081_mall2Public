package com.controller;
import com.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {
    @Autowired
    private CartService cs;
    @GetMapping("/cart")
    public Map<String ,Object> sel(int uid){
        List<Cart> carts = cs.selectByUserId(uid);
        int count=0;
        double totalPrice=0;
        for (Cart c:carts) {
            count+=c.getCount();
            totalPrice+=c.getTotalPrice();
        }
        Map<String ,Object>map=new HashMap<>();
        map.put("count",count);
        map.put("totalPrice",totalPrice);
        map.put("carts",carts);
        return map;
    }

    @PostMapping("/cart")
    public String ins(@RequestBody Cart cart){
        System.out.println(cart);
        String status="";
        Cart carts = cs.selectByPid(cart.getPid());
        if(carts==null){
            status="success";
            cs.insertOne(cart);
        }else {
            carts.setCount(carts.getCount()+1);
            carts.setTotalPrice(carts.getProduct().getPrice()*carts.getCount());
            cs.updateOne(carts);
         status="res";
        }
        return status;
    }
    //修改数量和价格
    @PutMapping("/cart")
    public String upc(@RequestBody Cart cart){
        String status="";
        try {
            status="success";
            cs.updateByCountId(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    //根据Id删除
    @DeleteMapping("/cart")
    public String del(int id){
        String status="";
        try {
            status="success";
            cs.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    @DeleteMapping("/cart/ids")
    public String dels(int[] ids){
        for (int i:ids
             ) {
            System.out.println(i);
        }
        String status="";
        try {
            status="success";
            cs.deleteMany(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
