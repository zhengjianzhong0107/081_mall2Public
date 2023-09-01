package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("comment")
public class Comment {
    @TableId(value = "com_id",type = IdType.AUTO)
    private int id;
    @TableField("com_content")
    private String content;
    @TableField("com_rate")
    private double rate;
    private int pid;
    private int uid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("dateComment")
    private Date dateComment;
    @TableField(exist = false)
    private Order order;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Product product;

    public Comment() {
    }

    public Comment(String content, double rate, int uid,int pid) {
        this.content = content;
        this.rate = rate;
        this.pid = pid;
        this.uid = uid;
    }

    public Comment(int id, String content, double rate, int pid, int uid) {
        this.id = id;
        this.content = content;
        this.rate = rate;
        this.pid = pid;
        this.uid = uid;
    }

    public Comment(int pid, int uid) {
        this.pid = pid;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rate=" + rate +
                ", pid=" + pid +
                ", uid=" + uid +
                ", dateComment=" + dateComment +
                ", order=" + order +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
