package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Collect;

import java.util.List;

public interface CollectDao extends BaseMapper<Collect> {
//    用户中心收藏
    List<Collect> selectAll(int uid);
    //取消收藏
    void deleteOne(Collect collect);
    //是否被收藏
    Collect selIf(Collect collect);
}
