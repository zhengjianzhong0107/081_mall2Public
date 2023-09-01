package com.controller;

import com.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.AddressService;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService as;
    //查询地址
    @GetMapping("/address")
    public List<Address> sel(int uid){
       return as.selectAll(uid);
    }
//    更新状态
    @PutMapping("/address/set")
    public String set(int uid,int id){
        String status="";
        try {
            status="success";
            List<Address> addresses=as.selectAll(uid);
            for (Address ad:addresses
                 ) {
                as.updateByIds(ad.getId(),0,0);
            }
            as.updateByIds(id,1,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
//    删除收货地址
    @DeleteMapping("/address/{id}")
    public String update(@PathVariable int id){
        String status="";
        try {
            status="success";
            as.updateByIds(id,0,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
//    添加一个收货地址
    @PostMapping("/address")
    public String add(@RequestBody Address address){
        String status="";
        try {
            status="success";
            as.insertOne(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
//    查询当前收货地址
    @GetMapping("/address/one")
    public Address selectOne(int id){
        return as.selectById(id);
    }
//    更新当前收货地址信息
    @PutMapping("/address/update")
    public String update(@RequestBody Address address){
        String status="";
        try {
            status="success";
            as.update(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
