package com.service.impl;

import com.dao.SysDao;
import com.entity.Sys;
import com.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //替换bean标签，创建对象
public class SysServiceImpl implements SysService {
    @Autowired
    private SysDao sd;
    @Override
    public Sys selectById(int id) {
        return sd.selectById(id);
    }

    @Override
    public void update(Sys system) {
        sd.updateById(system);
    }
}
