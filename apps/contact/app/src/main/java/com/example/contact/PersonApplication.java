package com.example.contact;

import android.app.Application;

import java.util.ArrayList;

public class PersonApplication extends Application {
    public static ArrayList<Person> p;
    @Override
    public void onCreate() {
        super.onCreate();
        p=new ArrayList<Person>();
        p.add(new Person("Pradeep","5648821545"));
        p.add(new Person("prabhu","8754963214"));
        p.add(new Person("teja","5489621457"));
        p.add(new Person("ravi","5621345879"));
        p.add(new Person("Pradeep","5648821545"));
        p.add(new Person("prabhu","8754963214"));
        p.add(new Person("teja","5489621457"));
        p.add(new Person("ravi","5621345879"));
        p.add(new Person("Pradeep","5648821545"));
        p.add(new Person("prabhu","8754963214"));
        p.add(new Person("teja","5489621457"));
        p.add(new Person("ravi","5621345879"));
    }
}
