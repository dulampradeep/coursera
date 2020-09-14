package com.example.postboxoriginal;

import android.app.Application;

import com.backendless.Backendless;

public class BackendConnect extends Application {
    public static final String server="https://api.backendless.com";
    public static final String APPID="2612064A-7CFE-46FD-FFFE-31C9BCBF0000";
    public static final String APIID="4D36D42A-DE9F-43CD-8F8D-841F33C130CC";

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(server);
        Backendless.initApp(getApplicationContext(), APPID, APIID);

    }
}
