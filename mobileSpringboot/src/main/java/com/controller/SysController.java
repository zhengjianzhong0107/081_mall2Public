package com.controller;

import com.entity.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.service.SysService;

import java.io.File;
import java.io.IOException;

@RestController
public class SysController {
    @Autowired
    private SysService ss;
    @GetMapping("/sys")
    public Sys sel(){
       return ss.selectById(1);
    }
    //上传favicon.ico
    @PostMapping("/ico")
    public String upload(MultipartFile myImg) throws IOException {
        //把文件复制给d盘
        //得到上传时用户上传的文件名
        String fileName=myImg.getOriginalFilename();
        File targetFile=new File("http://121.40.159.226:8989/assets/"+fileName);
        //开始赋值
        myImg.transferTo(targetFile);

        return fileName;
    }
    @PostMapping("/logo")
    public String uploadLogo(MultipartFile myImg1) throws IOException {
        //把文件复制给d盘
        //得到上传时用户上传的文件名
        String fileName=myImg1.getOriginalFilename();
        File targetFile=new File("http://121.40.159.226:8989/assets/"+fileName);
        //开始赋值
        myImg1.transferTo(targetFile);

        return fileName;
    }
    @PutMapping("/sys")
    public String update(@RequestBody Sys system) throws IOException {
        String status="";
        try {
            status="success";
            ss.update(system);
        } catch (Exception e) {
            status="error";
            e.printStackTrace();
        }
        return status;
    }
}
