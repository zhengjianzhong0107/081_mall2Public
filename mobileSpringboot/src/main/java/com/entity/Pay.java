package com.entity;

public class Pay {
    private String appId;
    private String priKey;
    private String pubKey;
    private String notify_url;
    private String return_url;

    public Pay() {
    }

    public Pay(String appId, String priKey, String pubKey, String notify_url, String return_url) {
        this.appId = appId;
        this.priKey = priKey;
        this.pubKey = pubKey;
        this.notify_url = notify_url;
        this.return_url = return_url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "appId='" + appId + '\'' +
                ", priKey='" + priKey + '\'' +
                ", pubKey='" + pubKey + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", return_url='" + return_url + '\'' +
                '}';
    }
}
