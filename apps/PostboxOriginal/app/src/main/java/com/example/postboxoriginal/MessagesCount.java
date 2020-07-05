package com.example.postboxoriginal;

public class MessagesCount {
    private String userid;
    private int count;

    public MessagesCount() {
        this.userid =null;
        this.count = 0;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
