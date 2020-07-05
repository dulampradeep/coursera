package com.example.connectdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);

    }
   public void btnsub(View v){
        String name=ed1.getText().toString().trim();
        String pass=ed2.getText().toString().trim();
        try {
            ContactsDb  c=new ContactsDb(MainActivity.this);
            c.open();
            c.createEntry(name,pass);
            c.close();
            Toast.makeText(this,"succcess insert",Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void btnshow(View v){
        Intent n=new Intent(this,Activity2.class);

        ContactsDb  c=new ContactsDb(MainActivity.this);
        c.open();
        String x=c.getData();
        c.close();
        n.putExtra("data",x);
        startActivity(n);

    }
   public void btnedit(View v){
       ContactsDb  c=new ContactsDb(MainActivity.this);
       c.open();
        c.UpdateEntry(2,ed1.getText().toString().trim(),ed2.getText().toString().trim());
       c.close();

    }
   public void btndelete(View v){
       ContactsDb  c=new ContactsDb(MainActivity.this);
       c.open();
        c.deleteEntry(2);
       c.close();
    }
}
