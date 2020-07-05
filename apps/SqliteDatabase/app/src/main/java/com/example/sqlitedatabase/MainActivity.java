package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2,b3,b4;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv1);
        e1=findViewById(R.id.ed1);
        e2=findViewById(R.id.ed2);
        e3=findViewById(R.id.ed3);
        b1=findViewById(R.id.b);
        b2=findViewById(R.id.b1);
        b3=findViewById(R.id.b2);
        b4=findViewById(R.id.b3);
        final SQLiteDatabase db;
        db=openOrCreateDatabase("baluproject",MODE_PRIVATE,null);
        try {
            db.execSQL("create table balu(id INTEGER PRIMARY KEY,name VARCHAR(10),password VARCHAR(10));");
        }
        catch (Exception e){
            e.printStackTrace(); Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues v1=new ContentValues();
                v1.put("id",e1.getText().toString().trim());
                v1.put("name",e2.getText().toString().trim());
                v1.put("password",e3.getText().toString().trim());
                long k=db.insert("baluproject",null,v1);
                if(k==-1){
                    Toast.makeText(MainActivity.this,"Not inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
       b4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor c= db.rawQuery("select * from baluproject",null);
               c.moveToFirst();
               String t="";
               while(!c.isAfterLast()){
                   t+=c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n";
                   c.moveToNext();
               }
               tv.setText(t);
           }
       });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }
}
