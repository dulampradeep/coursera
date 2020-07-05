package com.example.postboxoriginal;

import java.util.ArrayList;

public class AttendenceFormat{
    private String teacherid;
    private ArrayList<String> students;
    private String time;
    private String timemillis;

    public AttendenceFormat() {
        this.teacherid = null;
        this.students = null;
        this.time = null;
        this.timemillis=null;
    }

    public String getTimemillis() {
        return timemillis;
    }

    public void setTimemillis(String timemillis) {
        this.timemillis = timemillis;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
