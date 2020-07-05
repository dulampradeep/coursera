package com.example.tornado;

public class Person {
    private String name;
    private String pass;
    public Person() {
        name=null;
        pass=null;
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
