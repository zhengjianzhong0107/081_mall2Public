package com.service;

import com.entity.Advert;

import java.util.List;

public interface AdvertService {
    //查询轮播图广告以及消息
    List<Advert> selectGg(String tj);
    //查询公告,右侧
    Advert selectOne(int id);
    //修改广告
    void updateById(Advert advert);
}
