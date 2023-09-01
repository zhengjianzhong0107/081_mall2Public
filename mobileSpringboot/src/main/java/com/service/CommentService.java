package com.service;

import com.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    //后台评论查询
    PageInfo<Comment> selectByPage(int pageNo, int pageSize, String comment);
    //用户中心评论
    PageInfo<Comment> selectByUser(int pageNo,int pageSize,String comment,int id);
    //前台评论查询
    List<Comment> selectPid(int pid);
    //添加评论
    void insertO(Comment comment);
    //删除
    void delete(int id);
    //删除多个
    void deletes(int[] ids);
    //统计评论
    int selectCount();
    //是否被评论
    boolean exitCom(int uid,int pid);
}
