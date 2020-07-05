package com.example.carowner;

import android.app.Application;

import java.util.ArrayList;

public class CarApplication extends Application {
   static ArrayList<CarInform> n;
    @Override
    public void onCreate() {
        super.onCreate();
        n=new ArrayList<CarInform>();
        n.add(new CarInform(1,"Polo","pradeep","5478964210"));
        n.add(new CarInform(2,"Almera","prabhu","54764210"));
        n.add(new CarInform(0,"E200","ramesh","5483532213"));
        n.add(new CarInform(1,"Polo","pradeep","5478964213"));
        n.add(new CarInform(2,"Almera","prabhu","54764213"));
        n.add(new CarInform(0,"E200","ramesh","5483502210"));
        n.add(new CarInform(1,"Polo","pradeep","5478964210"));
        n.add(new CarInform(2,"Almera","prabhu","54764210"));
        n.add(new CarInform(0,"E200","ramesh","5483532213"));
        n.add(new CarInform(1,"Polo","pradeep","5478964213"));
        n.add(new CarInform(2,"Almera","prabhu","54764213"));
        n.add(new CarInform(0,"E200","ramesh","5483502210"));
        n.add(new CarInform(1,"Polo","pradeep","5478964210"));
        n.add(new CarInform(2,"Almera","prabhu","54764210"));
        n.add(new CarInform(0,"E200","ramesh","5480502210"));
        n.add(new CarInform(1,"Polo","pradeep","5478964210"));
        n.add(new CarInform(2,"Almera","prabhu","54764210"));
        n.add(new CarInform(0,"E200","ramesh","5480502210"));

    }
}
