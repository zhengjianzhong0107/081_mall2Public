package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys")
public class Sys {
    private int id;
    private String title;
    private String keyWord;
    private String description;
    private String ico;
    private String logo;
    public Sys() {
    }

    public Sys(int id, String title, String keyWord, String description, String ico, String logo) {
        this.id = id;
        this.title = title;
        this.keyWord = keyWord;
        this.description = description;
        this.ico = ico;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "System{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", description='" + description + '\'' +
                ", ico='" + ico + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
