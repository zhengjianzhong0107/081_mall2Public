package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @TableId(value = "product_id",type = IdType.AUTO)
    private int id;
    @TableField("cid")
    private int cid;
    @TableField("product_name")
    private String proName;
    @TableField("product_price")
    private double price;
    @TableField("groundingDate")
    @JsonFormat(pattern =
    "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date groundingDate;
    private int inventory;
    @TableField("salenum")
    private int saleNum;
    @TableField("messages")
    private String message;
    private String color;
    private int ram;
    @TableField("product_image")
    private String image;
    private int state;
    private double rate;
    @TableField(exist = false)
    private Category category;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(int id, int cid, String proName, Date groundingDate, int inventory, int saleNum, String message, String color, int ram,double price, String image, int state) {
        this.id = id;
        this.cid = cid;
        this.proName = proName;
        this.price = price;
        this.groundingDate = groundingDate;
        this.inventory = inventory;
        this.saleNum = saleNum;
        this.message = message;
        this.color = color;
        this.ram = ram;
        this.image = image;
        this.state = state;
    }

    public Product(int cid, String proName,  int inventory, int saleNum, String message, String color, int ram, double price,String image, int state) {
        this.cid = cid;
        this.proName = proName;
        this.price = price;
        this.inventory = inventory;
        this.saleNum = saleNum;
        this.message = message;
        this.color = color;
        this.ram = ram;
        this.image = image;
        this.state = state;
    }

    public Product(int cid, String proName, double price, Date groundingDate, int inventory, int saleNum, String message, String color, int ram, String image) {
        this.cid = cid;
        this.proName = proName;
        this.price = price;
        this.groundingDate = groundingDate;
        this.inventory = inventory;
        this.saleNum = saleNum;
        this.message = message;
        this.color = color;
        this.ram = ram;
        this.image = image;
    }

    public Product(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public Product(int id, double rate) {
        this.id = id;
        this.rate = rate;
    }

    public Product(int id, int inventory, int saleNum) {
        this.id = id;
        this.inventory = inventory;
        this.saleNum = saleNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Date getGroundingDate() {
        return groundingDate;
    }

    public void setGroundingDate(Date groundingDate) {
        this.groundingDate = groundingDate;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", cid=" + cid +
                ", proName='" + proName + '\'' +
                ", price=" + price +
                ", groundingDate=" + groundingDate +
                ", inventory=" + inventory +
                ", saleNum=" + saleNum +
                ", message='" + message + '\'' +
                ", color='" + color + '\'' +
                ", ram=" + ram +
                ", image='" + image + '\'' +
                ", state=" + state +
                ", rate=" + rate +
                ", category=" + category +
                '}';
    }
}
