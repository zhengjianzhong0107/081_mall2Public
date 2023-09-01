package com.service.impl;

import com.dao.CollectDao;
import com.entity.Collect;
import com.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao cd;
    @Override
    public void insertOne(Collect collect) {
        cd.insert(collect);
    }

    @Override
    public void deleteOne(Collect collect) {
       cd.deleteOne(collect);
    }

    @Override
    public List<Collect> select(int uid) {
      return cd.selectAll(uid);
    }
//判断是否被收藏
    @Override
    public boolean sel(Collect collect) {
        return cd.selIf(collect)!=null;
    }
}
