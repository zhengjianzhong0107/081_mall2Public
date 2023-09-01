package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Collect {
    @TableId(type = IdType.AUTO)
    private int id;
    private int uid;
    private int pid;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    public Collect() {
    }

    public Collect(int id, int uid, int pid) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
    }

    public Collect(int uid, int pid) {
        this.uid = uid;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
