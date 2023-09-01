package com.controller;

import com.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.CollectService;

import java.util.List;

@RestController
public class CollectController {
    @Autowired
    private CollectService cs;
    //添加
    @PostMapping("/collect")
    public String ins(@RequestBody Collect collect){
        String status="";
        try {
            status="success";
            cs.insertOne(collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    //删除
    @DeleteMapping("/collect")
    public String del(@RequestBody Collect collect){
        String status="";
        try {
            status="success";
            cs.deleteOne(collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    //查询
    @GetMapping("/collect")
    public List<Collect>sel(int uid){
        return cs.select(uid);
    }
    //判断收否被收藏
    @GetMapping("/collect/if")
    public boolean ifc(Collect collect){
        System.out.println(collect);
        return cs.sel(collect);
    }
}
