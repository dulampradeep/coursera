package com.example.recyclerview;

public class Person {
    private int img;
    private String name,surname;
    public Person(int n,String name,String surname){
        img=n;
        this.name=name;
        this.surname=surname;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
