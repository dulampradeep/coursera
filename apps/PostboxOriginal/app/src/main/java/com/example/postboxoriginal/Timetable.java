package com.example.postboxoriginal;

public class Timetable {
    private String hour;
    private String college;
    private String year;
    private String sec;
    private String branch;
    private String subname;
    private String day;

    public Timetable() {
        this.hour =null;
        this.college = null;
        this.year = null;
        this.sec = null;
        this.branch = null;
        this.subname = null;
        this.day=null;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }
}
