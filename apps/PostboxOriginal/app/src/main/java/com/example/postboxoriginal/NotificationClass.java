package com.example.postboxoriginal;

public class NotificationClass{
private String teacherid;
private String userid;
private Timetable timetable;
private String time;
private Boolean status;
private String timemillis;

    public NotificationClass() {
        this.teacherid=null;
        this.userid=null;
        timetable=null;
        time=null;
        timemillis=null;
        status=false;
    }

    public String getTimemillis() {
        return timemillis;
    }

    public void setTimemillis(String timemillis) {
        this.timemillis = timemillis;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }
}
