package com.controller;

import com.entity.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.service.PayService;

@RestController
public class PayController {
    @Autowired
    private PayService ps;
    @GetMapping("/pay")
    public Pay sel(){
        return ps.selectPay();
    }
    @PutMapping("/pay")
    public String update(@RequestBody Pay pay){
        String status="";
        try {
            status="success";
            ps.updatePay(pay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
