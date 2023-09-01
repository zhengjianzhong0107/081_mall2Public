package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.CommentDao;
import com.dao.ProductDao;
import com.entity.Comment;
import com.entity.Product;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao cd;
    @Autowired
    private ProductDao pd;
    @Override
    public PageInfo<Comment> selectByPage(int pageNo, int pageSize, String comment) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(cd.selectAll(comment));
    }

    @Override
    public PageInfo<Comment> selectByUser(int pageNo, int pageSize, String comment, int id) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(cd.selectYh(comment,id));
    }

    @Override
    public List<Comment> selectPid(int pid) {
        return cd.selectPid(pid);
    }

    @Override
    public void insertO(Comment comment) {
        cd.insertOne(comment);
        List<Comment> comments = cd.selectPid(comment.getPid());
        double rate=0;
        for (Comment co:comments
             ) {
            rate+=co.getRate();
        }
        pd.updateR(new Product(comment.getPid(),(rate/comments.size())));
    }

    @Override
    public void delete(int id) {
        cd.deleteById(id);
    }

    @Override
    public void deletes(int[] ids) {
        for (int i:ids
             ) {
            cd.deleteById(i);
        }
    }

    @Override
    public int selectCount() {
        return cd.selectCount();
    }

    @Override
    public boolean exitCom(int uid, int pid) {
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.and(o->o.eq("pid",pid).and(i->i.eq("uid",uid)));
        List<Comment> comments = cd.selectList(queryWrapper);
        return comments.size() == 0;
    }
}
