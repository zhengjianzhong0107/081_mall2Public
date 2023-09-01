package com.service.impl;

import com.dao.MailDao;
import com.entity.Mail;
import com.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailDao md;
    @Override
    public Mail selectById(int id) {
        return md.selectById(id);
    }

    @Override
    public void updateById(Mail mail) {
       md.updateById(mail);
    }
}
