package com.example.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView t;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        t=findViewById(R.id.tv1);
        b1=findViewById(R.id.b1);
        final String name=getIntent().getStringExtra("name");
        t.setText(name+"hai There Welcome Intent ");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(Activity2.this,com.example.intenttest.MainActivity.class);
                n.putExtra("name",name);
                startActivity(n);
            }
        });
    }
}
