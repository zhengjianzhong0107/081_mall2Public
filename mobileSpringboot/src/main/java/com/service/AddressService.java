package com.service;

import com.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {
    //通过用户id查询收货地址
    List<Address> selectAll(int uid);
    //暂时删除以及设置默认
    void updateByIds(@Param("id")int id, @Param("sid")int sid, @Param("del")int del);
    //添加
    void insertOne(Address address);
    //通过地址ID查询当前地址
    Address selectById(int id);
    //修改地址
    void update(Address address);
}
