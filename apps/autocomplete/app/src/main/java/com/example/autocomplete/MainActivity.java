package com.example.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
AutoCompleteTextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.ed1);
        t2=findViewById(R.id.ed2);
        String names[]={"dp","hai","jack","pavan","pradeep","teja","sai","bala"};
        ArrayAdapter<String> adp
                =new ArrayAdapter<String>(this,R.layout.mylayout,names);
        t1.setThreshold(1);
        t1.setAdapter(adp);
        t2.setThreshold(2);
        t2.setAdapter(adp);
    }
}
