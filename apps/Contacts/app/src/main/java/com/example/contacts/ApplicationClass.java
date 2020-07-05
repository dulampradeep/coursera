package com.example.contacts;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person> p;
    @Override
    public void onCreate() {
        super.onCreate();
        p=new ArrayList<Person>();
        p.add(new Person("pradeep","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("praee","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("prp","8957656254"));
        p.add(new Person("pradeep","89556254"));
        p.add(new Person("pradeep","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("praee","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("prp","8957656254"));
        p.add(new Person("pradeep","89556254"));
        p.add(new Person("pradeep","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("praee","8957656254"));
        p.add(new Person("prep","8957656254"));
        p.add(new Person("prp","8957656254"));
        p.add(new Person("pradeep","89556254"));
    }
}
