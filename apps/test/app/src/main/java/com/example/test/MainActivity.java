package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    TabHost t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=findViewById(R.id.tabhost);
        ActionBar a=getSupportActionBar();
        a.hide();
        t.setup();
        TabHost.TabSpec spe=t.newTabSpec("tag1");
        spe.setContent(R.id.tab1);
        spe.setIndicator("first");
        t.addTab(spe);
        spe=t.newTabSpec("tag2");
        spe.setContent(R.id.tab2);
        spe.setIndicator("second");
        t.addTab(spe);
        spe=t.newTabSpec("tag3");
        spe.setContent(R.id.tab3);
        spe.setIndicator("third");
        t.addTab(spe);
        spe=t.newTabSpec("tag4");
        spe.setContent(R.id.tab4);
        spe.setIndicator("fourth");
        t.addTab(spe);
        spe=t.newTabSpec("tag5");
        spe.setContent(R.id.tab5);
        spe.setIndicator("fifth");
        t.addTab(spe);
        spe=t.newTabSpec("tag6");
        spe.setContent(R.id.tab6);
        spe.setIndicator("sixth");
        t.addTab(spe);
        spe=t.newTabSpec("tag7");
        spe.setContent(R.id.tab7);
        spe.setIndicator("seventh");
        t.addTab(spe);
    }
}
