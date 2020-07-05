package com.example.backendless;

import android.app.Application;

import com.backendless.Backendless;

public class BackendLess extends Application {
    public static final String appid="91BBD410-386B-E5F4-FFBB-62DD6B708400";
    public static final String apikey="AE834D68-3AF3-4C2E-ADE6-38640CEA4895";
    public static final String server_url="https://api.backendless.com";
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(server_url);
        Backendless.initApp(getApplicationContext(),appid,apikey);
    }
}
