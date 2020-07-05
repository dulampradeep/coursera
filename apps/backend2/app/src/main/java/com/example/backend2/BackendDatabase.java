package com.example.backend2;

import android.app.Application;

import com.backendless.Backendless;

public class BackendDatabase extends Application {
    public static final String APPLICATION_ID = "91BBD410-386B-E5F4-FFBB-62DD6B708400";
    public static final String API_KEY = "AE834D68-3AF3-4C2E-ADE6-38640CEA4895";
    public static final String SERVER_URL = "http://api.backendless.com";
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );
    }
}
