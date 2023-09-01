package com.service.impl;

import com.entity.Pay;
import org.springframework.stereotype.Service;
import com.service.PayService;

import java.io.*;
import java.util.Properties;

@Service
public class PayServiceImpl implements PayService {
    @Override
    public Pay selectPay() {
        Pay pay=new Pay();
        //创建出properties
        Properties properties=new Properties();
        //把文本文档转换为一个流
        InputStream is=PayServiceImpl.class.getResourceAsStream("/common.properties");
        //调用load方法去加载文本文档的数据
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pay.setAppId(properties.getProperty("app_id")) ;
        pay.setPriKey(properties.getProperty("merchant_private_key"));
        pay.setPubKey(properties.getProperty("alipay_public_key"));
        pay.setNotify_url(properties.getProperty("notify_url"));
        pay.setReturn_url(properties.getProperty("return_url"));
        return pay;
    }

    @Override
    public void updatePay(Pay pay) {
        Properties pro=new Properties();
        InputStream in=PayServiceImpl.class.getResourceAsStream("/common.properties");
        try {
            pro.load(in);
            pro.setProperty("app_id", pay.getAppId());
            pro.setProperty("merchant_private_key", pay.getPriKey());
            pro.setProperty("alipay_public_key", pay.getPubKey());
            pro.setProperty("notify_url", pay.getNotify_url());
            pro.setProperty("return_url", pay.getReturn_url());
            OutputStream os = null;
            os = new FileOutputStream(PayServiceImpl.class.getResource("/common.properties").getPath());
            pro.store(os, null);
            os.flush();
            os.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
