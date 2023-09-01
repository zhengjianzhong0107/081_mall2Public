package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class Advert {
    private int id;
    private String img;
    private String title;
    private String url;
    @TableField(exist = false)
    private String tj;

    public String getTj() {
        return tj;
    }

    public void setTj(String tj) {
        this.tj = tj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Advert() {
    }

    public Advert(int id, String img, String url) {
        this.id = id;
        this.img = img;
        this.url = url;
    }

    public Advert(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Advert(int id, String img, String title, String url) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
