package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao extends BaseMapper<Comment> {
    //后台评论查询
    List<Comment> selectAll(@Param("comment")String comment);
//    用户中心的用户查看评论
    List<Comment> selectYh(@Param("comment")String comment,@Param("id")int id);
    //前台评论查询
    List<Comment> selectPid(@Param("pid")int pid);
    //添加评论
    void insertOne(Comment comment);
    //统计评论
    int selectCount();
}
