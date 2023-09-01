package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class Mail {
    private int id;
    private String host;
    private int port;
    private String email;
    private String pwd;
    @TableField("nickName")
    private String nickName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Mail() {
    }

    public Mail(int id, String host, int port, String email, String pwd, String nickName) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.email = email;
        this.pwd = pwd;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
