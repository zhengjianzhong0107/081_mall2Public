package com.controller;

import com.github.pagehelper.PageInfo;
import com.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.CategoryService;

import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    private CategoryService cs;

    //查询所有类别
    @GetMapping("/cate/count")
    public PageInfo<Category> sel(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "5")int pageSize){
        return cs.selectByPage(pageNo, pageSize);
    }
    //查询所有类别
    @GetMapping("/cate")
    public List<Category> count(){
        return cs.selectAll();
    }
//    添加类别
    @PostMapping("/cate")
    public String add(@RequestBody Category cate){
        String status="";
        try {
            status="success";
            cs.insert(cate);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //    更新类别
    @PutMapping("/cate")
    public String update(@RequestBody Category cate){
        String status="";
        try {
            status="success";
            cs.update(cate);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //    删除类别
    @DeleteMapping("/cate")
    public String delete(Integer id){
        String status="";
        try {
            status="success";
            cs.delete(id);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping("/cate/one")
    public String selectById(int id){
        return cs.selectById(id).getName();
    }
    @GetMapping("/cate/num")
    public int selectCount(){
        return cs.selectCount();
    }
}
