package com.service;

import com.entity.Mail;

public interface MailService {
    Mail selectById(int id);
    void updateById(Mail mail);
}
