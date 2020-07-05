package com.example.tornado;

import android.app.Application;

import com.backendless.Backendless;

public class ServerConnection extends Application {
    public static final String APPID="975C3E58-104C-F07E-FF2A-03F7FD5A7E00";
    public static final String APIKEY="3189810C-6A71-4EFE-8E00-0E93A1849D95";
    public static final String Serverurl="https://api.backendless.com";
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(Serverurl);
        Backendless.initApp(getApplicationContext(),APPID,APIKEY);
    }
}
