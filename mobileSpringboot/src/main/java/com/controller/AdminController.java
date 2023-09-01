package com.controller;

import com.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.service.AdminService;
import com.util.TokenUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService as;

    @GetMapping("/admin/login")
    public Map<String ,Object> login(@RequestParam("name")String name, @RequestParam("pwd")String pwd){
       Map<String,Object>map=new HashMap<>();
        boolean login = as.login(name, pwd);
        String status="";
        if(login){
            Admin admin = as.selectByNameAndPwd(name, pwd);
            String token = TokenUtil.sign((String) map.get("userName"));
            status= "success" ;
            map.put("status",status);
            map.put("admin",admin);
            map.put("token",token);
        }else {
            status= "error";
            map.put("status",status);
        }
        return map;
    }
    //查询
    @GetMapping("/admin")
    public Admin selectById(int id){
        return as.selectById(id);
    }
    @PostMapping("/admin/upload")
    public Map<String,Object> upload(MultipartFile myImg) throws IOException {
        //把文件复制给d盘
        //得到上传时用户上传的文件名
        String fileName=myImg.getOriginalFilename();
        File targetFile=new File("H:\\mobile\\src\\assets/avatar/"+fileName);
        //开始赋值
        myImg.transferTo(targetFile);

        //保存数据前给属性赋值
//        stu.setHeadImg(fileName);
        Map<String,Object> map=new HashMap<>();
        String newAvatar="http://localhost:8081/assets/avatar/"+fileName;
        map.put("avatarPath",newAvatar);
        return map;
    }
    //更新信息
    @PutMapping("/admin")
    public String update(@RequestBody Admin admin){
        String status="";
        try {
            status="success";
            as.updateById(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
