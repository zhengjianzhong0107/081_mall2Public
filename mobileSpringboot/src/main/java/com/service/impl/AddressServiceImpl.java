package com.service.impl;

import com.dao.AddressDao;
import com.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.AddressService;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao ad;
    //查询收货地址
    @Override
    public List<Address> selectAll(int uid) {
        return ad.selectAll(uid);
    }
//删除和设置默认
    @Override
    public void updateByIds(int id, int sid, int del) {
        ad.updateByIds(id,sid,del);
    }
//添加一个收货地址
    @Override
    public void insertOne(Address address) {
        ad.insertOne(address);
    }
//根据ID查询当前地址
    @Override
    public Address selectById(int id) {
        return ad.selectById(id);
    }

    //更新当前收货地址
    @Override
    public void update(Address address) {
        ad.update(address);
    }
}
