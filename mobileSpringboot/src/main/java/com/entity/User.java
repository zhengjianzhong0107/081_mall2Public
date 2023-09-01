package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("user") //映射对应的数据库表
public class User {
    @TableId(value="user_id",type = IdType.AUTO )//映射数据库对应的字段
    private int id;
    @TableField("user_name")
    private String email;
    @TableField("user_pwd")
    private String pwd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("createTime")
    private Date createTime;
    private int age;
    private String sex;
    private String phone;
    private int state;
    @TableField("nickName")
    private String nickName;
    private String avatar;

    public User() {
    }
//用户更新信息

    public User(int id,String nickName, int age, String sex, String phone,String avatar) {
        this.id=id;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public User(String email, String pwd, Date createTime, int state) {
        this.email = email;
        this.pwd = pwd;
        this.createTime = createTime;
        this.state = state;
    }

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
//后台添加用户

    public User(String email, String pwd, int state, String nickName) {
        this.email = email;
        this.pwd = pwd;
        this.state = state;
        this.nickName = nickName;
    }
//修改qq用户

    public User(String email, String nickName, String avatar) {
        this.email = email;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public User(String email, String pwd, int age, String sex, String phone, int state, String nickName, String avatar) {
        this.email = email;
        this.pwd = pwd;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.state = state;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public User(int id, String email, String pwd, Date createTime, int age, String sex, String phone, int state, String nickName, String avatar) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.createTime = createTime;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.state = state;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
