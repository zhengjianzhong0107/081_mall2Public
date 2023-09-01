package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@TableName("d_order")
public class Order {
    private String id;
    @TableField("good_price")
    private double goodPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("order_time")
    private Date orderTime;
    @TableField("order_state")
    private int orderState;
    @TableField("addr_id")
    private int addrId;
    @TableField("user_id")
    private int userId;
    @TableField(exist = false)
    private List<OrderDetails> orderDetails;
    //后台展示管理
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order(String id, double goodPrice, int orderState, int addrId, int userId) {
        this.id = id;
        this.goodPrice = goodPrice;
        this.orderState = orderState;
        this.addrId = addrId;
        this.userId = userId;
    }

    public Order(String id, double goodPrice, Date orderTime, int orderState, int addrId, int userId, List<OrderDetails> orderDetails) {
        this.id = id;
        this.goodPrice = goodPrice;
        this.orderTime = orderTime;
        this.orderState = orderState;
        this.addrId = addrId;
        this.userId = userId;
        this.orderDetails = orderDetails;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order() {
    }

    public Order(String id, double goodPrice, Date orderTime, int orderState, int addrId, int userId) {
        this.id = id;
        this.goodPrice = goodPrice;
        this.orderTime = orderTime;
        this.orderState = orderState;
        this.addrId = addrId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodPrice=" + goodPrice +
                ", orderTime=" + orderTime +
                ", orderState=" + orderState +
                ", addrId=" + addrId +
                ", userId=" + userId +
                ", orderDetails=" + orderDetails +
                ", user=" + user +
                ", address=" + address +
                '}';
    }
}
