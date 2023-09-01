package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Advert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertDao extends BaseMapper<Advert> {
    //查询轮播图广告以及消息
    List<Advert> selectGg(@Param("tj") String tj);
    //查询公告,右侧
    Advert selectOne(@Param("id")int id);
    //修改广告

}
