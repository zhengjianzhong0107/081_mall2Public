package com.controller;

import com.github.pagehelper.PageInfo;
import com.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.CommentService;
import com.service.OrderService;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService cs;
    @Autowired
    private OrderService os;
    @GetMapping("/comment")
    public PageInfo<Comment> slectByPage(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "6")int pageSize,String comment){
        return cs.selectByPage(pageNo, pageSize,comment);
    }
    @GetMapping("/comment/com")
    public List sel(int pid){
        return cs.selectPid(pid);
    }
   @DeleteMapping("/comment")
    public String del(int id){
        String status="";
       try {
           status="success";
           cs.delete(id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return status;
   }
   @DeleteMapping("/comment/ids")
    public String dels(int[] ids){
       String status="";
       try {
           status="success";
           cs.deletes(ids);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return status;
   }
   //统计评论
    @GetMapping("/comment/count")
    public int sele(){
        return cs.selectCount();
    }
    //用户中心查看评论
    @GetMapping("/comment/user")
    public PageInfo<Comment> slectByUser(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "6")int pageSize,String comment,int id){
        return cs.selectByUser(pageNo, pageSize,comment,id);
    }
    //判断是否添加评论
    @GetMapping("/comment/if")
    public String ifAdd(int id,int pid){
        boolean b = os.selectIfBuy(id, pid);
        boolean b1 = cs.exitCom(id, pid);
        if(b){
            if(b1){
                //商品未添加过评论
                return "200";
            }
            //商品已经添加过评论
            return "300";
        }
        //您还没购买
        return "400";
    }
    //添加评论
    @PostMapping("/comment")
    public String insert(@RequestBody Comment comment){
        System.out.println(comment);
        String status="";
        try {
            status="success";
            cs.insertO(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
