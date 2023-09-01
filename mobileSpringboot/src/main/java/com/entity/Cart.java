package com.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("cart")
public class Cart {
 @TableId(type= IdType.AUTO)
 private int id;
 private int pid;//商品id
 private int uid;//用户id
 private int count;
 @TableField("totalPrice")
 private double totalPrice;
 @TableField(exist = false)
 private Product product;
 @TableField(exist = false)
 private User user;

    public Cart(int pid, int uid, int count, double totalPrice) {
        this.pid = pid;
        this.uid = uid;
        this.count = count;
        this.totalPrice = totalPrice;
    }
//更新数量价格

    public Cart(int id, int count, double totalPrice) {
        this.id = id;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public Cart(int id, int pid, int uid, int count, double totalPrice, Product product, User user) {
  this.id = id;
  this.pid = pid;
  this.uid = uid;
  this.count = count;
  this.totalPrice = totalPrice;
  this.product = product;
  this.user = user;
 }

 public Cart() {
 }

 public Cart(int id, int pid, int uid, int count, double totalPrice) {
  this.id = id;
  this.pid = pid;
  this.uid = uid;
  this.count = count;
  this.totalPrice = totalPrice;
 }

 public Cart(int pid, int uid, int count) {
  this.pid = pid;
  this.uid = uid;
  this.count = count;
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

 public int getUid() {
  return uid;
 }

 public void setUid(int uid) {
  this.uid = uid;
 }

 public int getCount() {
  return count;
 }

 public void setCount(int count) {
  this.count = count;
 }

 public double getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(double totalPrice) {
  this.totalPrice = totalPrice;
 }

 public Product getProduct() {
  return product;
 }

 public void setProduct(Product product) {
  this.product = product;
 }

 public User getUser() {
  return user;
 }

 public void setUser(User user) {
  this.user = user;
 }

 @Override
 public String toString() {
  return "Cart{" +
          "id=" + id +
          ", pid=" + pid +
          ", uid=" + uid +
          ", count=" + count +
          ", totalPrice=" + totalPrice +
          ", product=" + product +
          ", user=" + user +
          '}';
 }
}