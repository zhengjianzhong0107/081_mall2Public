package com.controller;

import com.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.service.AdvertService;

import java.util.List;

@RestController
public class AdvertController {
    @Autowired
    private AdvertService as;
    //查询轮播以及通知
    @GetMapping("/adv")
    public List<Advert> sel(String tj){
        return as.selectGg(tj);
    }
    //查询公告以及右侧广告
    @GetMapping("/adv/one")
    public Advert selOne(int id){
        return as.selectOne(id);
    }
    //更新
    @PutMapping("/adv")
    public String update(@RequestBody Advert advert){
        String status="";
        try {
            status="success";
            as.updateById(advert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
