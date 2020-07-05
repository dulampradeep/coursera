package com.example.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity {
    EditText e;
    Button b,b7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        b=findViewById(R.id.act3b);
        e=findViewById(R.id.edact3);
        b7=findViewById(R.id.but2);
        String name=getIntent().getStringExtra("name");
        e.setText(name);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=e.getText().toString().trim();
                Intent n=new Intent();
                n.putExtra("name",data);
                setResult(RESULT_OK,n);
                Activity3.this.finish();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                Activity3.this.finish();
            }
        });
    }
}
