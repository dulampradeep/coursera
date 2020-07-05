package com.example.carowner;

public class CarInform {
    private int i;
    private String carnumber;
    private String name;
    private String phone;

    public CarInform(int i, String carnumber, String name, String phone) {
        this.i = i;
        this.carnumber = carnumber;
        this.name = name;
        this.phone = phone;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
