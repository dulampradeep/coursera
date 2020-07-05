package com.example.postbox;

import java.net.URI;

public class Admin {
    private String profilepic;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phno;
    private String branch;
    private String sec;
    private String year;
    private String college;
    private String address;
    private String role;
    private boolean emailverification;
    private String objectId;

    public Admin() {
        profilepic=null;
        username=null;
        password=null;
        name=null;
        email=null;
        phno=null;
        college=null;
        address=null;
        branch=null;
        sec=null;
        year=null;
        role=null;
        emailverification=false;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public boolean isEmailverification() {
        return emailverification;
    }

    public void setEmailverification(boolean emailverification) {
        this.emailverification = emailverification;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
