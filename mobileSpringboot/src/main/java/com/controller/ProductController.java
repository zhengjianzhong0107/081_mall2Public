package com.controller;

import com.entity.Product;
import com.github.pagehelper.PageInfo;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ProductController {
    private final static String OLD_PATH ="E:\\educationProject\\商城\\ssm\\mobile_mall-main\\mobileSpringboot\\src\\main\\webapp\\static\\assets/upload/";
    @Autowired
    private ProductService ps;
    //后台展示
    @GetMapping("/product")
    public PageInfo<Product>selectPage(@RequestParam(defaultValue = "1")int pageNo, @RequestParam(defaultValue = "6")int pageSize, @RequestParam(defaultValue = "0")int cid
    , String name, @RequestParam(defaultValue = "0")int min, @RequestParam(defaultValue = "0")int max){
        return ps.selectByPage(pageNo, pageSize, cid,name,min,max);
    }
    //前台首页最新上架 限制条数
    @GetMapping("/product/newest")
    public List<Product> recent(){
        return ps.selectLimit(9);
    }
    //手机详情页
    @GetMapping("/product/detail")
    public Product detail(int id){
        return ps.selectById(id);
    }
    //前台首页最新上架 限制条数
    @GetMapping("/product/pop")
    public List<Product> pop(){
        return ps.selectPop(9);
    }
    //根据所属分类cid查到商品
    @GetMapping("/product/cate")
    public PageInfo<Product> cate(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "10")int pageSize,@RequestParam(defaultValue = "0") int cid){
        return ps.selectCate(pageNo, pageSize, cid);
    }
    //更改商品上下架功能
    @PutMapping("/product/state")
    public String updateState(@RequestBody Product product){
        String status="";
        try {
            status="success";
            ps.updateState(product);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //删除商品
    @DeleteMapping("/product")
    public String delete(int id){
        String status="";
        try {
            status="success";
            ps.deleteById(id);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //删除多个商品
    @DeleteMapping("/product/many")
    public String deletes(int[] ids){
        String status="";
        try {
            status="success";
            ps.deleteMany(ids);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //1.上传商品图片
    @PostMapping("/product/upload")
    public Map<String,Object> upload(MultipartFile myImg) throws IOException {
        //把文件复制给d盘
        //得到上传时用户上传的文件名
        String fileName=myImg.getOriginalFilename();
        //给文件名重新赋值，避免两个文件名字相同被覆盖
        //fileName=System.currentTimeMillis()+fileName;
        fileName= UUID.randomUUID().toString()+fileName;
        System.out.println(fileName);

        String oldAvatar = OLD_PATH+fileName;
        File targetFile=new File(oldAvatar);
        //开始赋值
        myImg.transferTo(targetFile);

        //保存数据前给属性赋值
//        stu.setHeadImg(fileName);
        Map<String,Object> map=new HashMap<>();
        String newAvatar="http://localhost:8081/assets/upload/"+fileName;
        map.put("avatarPath",newAvatar);
        return map;
    }
//后台添加商品
    @PostMapping("/product")
    public String ins(@RequestBody Product product){
        String status="";
        try {
            status="success";
            ps.insertOne(product);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //后台更新商品
    @PutMapping("/product")
    public String update(@RequestBody Product product){
        String status="";
        try {
            status="success";
            ps.update(product);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
    //模糊查询,搜索
    @GetMapping("/product/dim")
    public PageInfo<Product>sel(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "10")int pageSize, String keyWord){
        return ps.selectDim(pageNo, pageSize, keyWord);
    }
//后台商品统计
    @GetMapping("/product/count")
    public int selectCount(){
        return ps.selectCount();
    }

}
