package com.example.customlist;

public class Product {
    private int type;
    private String name;
    private String model;
    private String desc;
    private boolean sale;

    public Product(int type, String name, String model, String desc,boolean sale) {
        this.type = type;
        this.name = name;
        this.model = model;
        this.desc = desc;
        this.sale=sale;
    }

    public boolean getSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
