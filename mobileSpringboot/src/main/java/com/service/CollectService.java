package com.service;

import com.entity.Collect;

import java.util.List;

public interface CollectService {
    void insertOne(Collect collect);
    void deleteOne(Collect collect);
    //用户中心收藏查询
    List<Collect> select(int uid);
    //判断是否被收藏
    boolean sel(Collect collect);
}
