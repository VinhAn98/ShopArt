package com.example.apptmdt;

import java.util.List;
import java.util.Map;

public class Product {
    private String id_pro,id_sub,name,price;
    private List<String> img_url;
    private Map<String,String> info;


    public Product(String id_pro, String id_sub, String name, String price, List<String> img_url, Map info) {
        this.id_pro = id_pro;
        this.id_sub = id_sub;
        this.name = name;
        this.price = price;
        this.img_url = img_url;
        this.info = info;
    }

    public Product() {
    }

    public String getId_pro() {
        return id_pro;
    }

    public void setId_pro(String id_pro) {
        this.id_pro = id_pro;
    }

    public String getId_sub() {
        return id_sub;
    }

    public void setId_sub(String id_sub) {
        this.id_sub = id_sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
