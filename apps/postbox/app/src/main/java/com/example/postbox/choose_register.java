package com.example.postbox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.persistence.local.UserIdStorageFactory;

import java.util.ArrayList;
import java.util.List;

public class choose_register extends AppCompatActivity {
    List l=new ArrayList();
    String item1;
    public static final String LOGINDETAILS="com.example.postbox.data";
    Spinner s;
    Button login,register;
    String objid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_register);
        SharedPreferences shar=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        String role=shar.getString("role","");
        String uname=shar.getString("mail","");
        String upass=shar.getString("pass","");
        Toast.makeText(choose_register.this,role+" "+uname+" "+upass,Toast.LENGTH_LONG).show();
        if(role!="" && uname!="" && upass!=""){
            if(role.toLowerCase().equals("student")) {
                startActivity(new Intent(choose_register.this,StudentMenu.class));
            }
            else {
                startActivity(new Intent(choose_register.this, MainActivity.class));
            }
            choose_register.this.finish();
        }
        ActionBar b=getSupportActionBar();
        b.hide();
        l.add(new String("Admin"));
        l.add(new String("Faculty"));
        l.add(new String("Student"));
        login=findViewById(R.id.adlogin);
        register=findViewById(R.id.adregis);
        ArrayAdapter adp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,l);
        adp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        s=findViewById(R.id.spinner);
        s.setAdapter(adp);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(choose_register.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1=s.getSelectedItem().toString().trim();
                if(item1.toLowerCase().equals("student")){
                    Intent n=new Intent(choose_register.this,StudentRegister.class);
                    n.putExtra("user",item1);
                    startActivity(n);
                }
                else{
                    Intent n=new Intent(choose_register.this,Register.class);
                    n.putExtra("user",item1);
                    startActivity(n);
            }}
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1=s.getSelectedItem().toString().trim();
                Intent n=new Intent(choose_register.this,Login.class);
                n.putExtra("user",item1);
                startActivity(n);
            }
        });
    }
}
