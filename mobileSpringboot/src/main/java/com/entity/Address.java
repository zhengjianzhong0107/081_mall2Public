package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("d_address")
public class Address {
    @TableId(value = "address_id",type = IdType.AUTO)
    private int id;
    @TableField("receive_name")
    private String name;
    private String address;
private String mobile;
private int uid;
    @TableField("delete_state")
    private int del;
    @TableField("selected_state")
    private int sid;
    @TableField(exist = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Address(int id, String name, String address, String mobile, int uid, int del, int sid) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.uid = uid;
        this.del = del;
        this.sid = sid;
    }

    public Address(String name, String address, String mobile, int uid) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.uid = uid;
    }
//修改收货地址

    public Address(int id, String name, String address, String mobile) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", uid=" + uid +
                ", del=" + del +
                ", sid=" + sid +
                '}';
    }
}
