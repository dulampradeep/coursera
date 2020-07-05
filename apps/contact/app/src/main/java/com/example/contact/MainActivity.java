package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SendIndex{
    TextView tname,tnum;
    EditText ename,enumb;
    Listfrag lis;
    Button but;
    FragmentManager fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tname=findViewById(R.id.tvname);
        tnum=findViewById(R.id.tvnum);
        ename=findViewById(R.id.edname);
        enumb=findViewById(R.id.ednum);
        but=findViewById(R.id.addcon);
        fr=this.getSupportFragmentManager();
        lis=(Listfrag)fr.findFragmentById(R.id.listfrag);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ename.getText().toString().isEmpty() && !enumb.getText().toString().isEmpty()){
                    PersonApplication.p.add(new Person(ename.getText().toString().trim(),enumb.getText().toString().trim()));
                    Toast.makeText(MainActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
                    lis.notifyDataChanged();
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed to add",Toast.LENGTH_SHORT).show();
                }
            }
        });
        senddata(0);
    }

    @Override
    public void senddata(int index) {
        tname.setText(PersonApplication.p.get(index).getName());
        tnum.setText(PersonApplication.p.get(index).getNum());
    }
}
