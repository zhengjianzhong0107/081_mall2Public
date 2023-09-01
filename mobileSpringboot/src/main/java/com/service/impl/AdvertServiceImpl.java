package com.service.impl;

import com.dao.AdvertDao;
import com.entity.Advert;
import com.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao ad;
    @Override
    public List<Advert> selectGg(String tj) {
        return ad.selectGg(tj);
    }

    @Override
    public Advert selectOne(int id) {
        return ad.selectOne(id);
    }

    @Override
    public void updateById(Advert advert) {
    ad.updateById(advert);
    }
}
