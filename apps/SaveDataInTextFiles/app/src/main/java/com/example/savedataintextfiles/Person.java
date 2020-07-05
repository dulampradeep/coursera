package com.example.savedataintextfiles;

public class Person {
    private String name;
    private String sur;

    public Person(String name, String sur) {
        this.name = name;
        this.sur = sur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSur() {
        return sur;
    }

    public void setSur(String sur) {
        this.sur = sur;
    }
}
