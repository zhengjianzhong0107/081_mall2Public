package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("order_item")
public class OrderDetails {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField("product_id")
    private int pid;
    @TableField("order_id")
    private String orderId;
    private int count;
    @TableField("unit_price")
    private double price;
    @TableField(exist = false)
    private Product product;

    public OrderDetails() {
    }

    public OrderDetails(int id, int pid, String orderId, int count, double price, Product product) {
        this.id = id;
        this.pid = pid;
        this.orderId = orderId;
        this.count = count;
        this.price = price;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", pid=" + pid +
                ", orderId=" + orderId +
                ", count=" + count +
                ", price=" + price +
                ", product=" + product +
                '}';
    }
}
