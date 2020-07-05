package com.example.backendless;

public class Person {
    private String name;
    private String pass;

    public Person() {
        this.name = null;
        this.pass = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
