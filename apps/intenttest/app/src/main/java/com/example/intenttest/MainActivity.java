package com.example.intenttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText e;
    TextView t;
    Button b1,b2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=findViewById(R.id.ed1);
        t=findViewById(R.id.textView2);
        b1=findViewById(R.id.act2);
        b2=findViewById(R.id.act3);
        String data=getIntent().getStringExtra("name");
        t.setText(data);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter your name",Toast.LENGTH_SHORT).show();
                }
                else{
                    String name=e.getText().toString().trim();
                    Intent i1=new Intent(MainActivity.this,com.example.intenttest.Activity2.class);
                    i1.putExtra("name",name);
                    startActivity(i1);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter your name",Toast.LENGTH_SHORT).show();
                }
                else{
                    String name=e.getText().toString().trim();
                    Intent n =new Intent(MainActivity.this,com.example.intenttest.Activity3.class);
                    n.putExtra("name",name);
                    startActivityForResult(n,3);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3){
            if(resultCode==RESULT_OK){
                t.setText(data.getStringExtra("name"));
            }
            if(resultCode==RESULT_CANCELED){
                t.setText("No data");
            }
        }
    }
}
