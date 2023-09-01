package com.service;

import com.entity.Pay;

public interface PayService {
    Pay selectPay();
    void updatePay(Pay pay);
}
