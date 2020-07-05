package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.PassItem {
    RecyclerView recy;
    RecyclerView.LayoutManager lm;
    RecyclerView.Adapter adp;
    ArrayList<Person> p;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy=findViewById(R.id.recycle);
        recy.setHasFixedSize(true);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.add(new Person(2, "hai", "hello"));
                adp.notifyDataSetChanged();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.remove(p.size()-1);
                adp.notifyDataSetChanged();

            }
        });
        //lm=new LinearLayoutManager(MainActivity.this);
       // lm=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        lm=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recy.setLayoutManager(lm);
        p=new ArrayList<Person>();
        p.add(new Person(1,"pradeep","dulam"));
        p.add(new Person(2,"pep","dulam"));
        p.add(new Person(2,"radeep","dula"));
        p.add(new Person(1,"prabhu","dulm"));
        p.add(new Person(2,"pade","dulam"));
        p.add(new Person(1,"padeep","dulam"));
        p.add(new Person(2,"peep","dulam"));
        p.add(new Person(2,"praep","dulam"));
        p.add(new Person(1,"pradeep","dulam"));
        p.add(new Person(2,"pep","dulam"));
        p.add(new Person(2,"radeep","dula"));
        p.add(new Person(1,"prabhu","dulm"));
        p.add(new Person(2,"pade","dulam"));
        p.add(new Person(1,"padeep","dulam"));
        p.add(new Person(2,"peep","dulam"));
        p.add(new Person(2,"praep","dulam"));

        adp=new PersonAdapter(this,p);
        recy.setAdapter(adp);
    }

    @Override
    public void getItemPa(int index) {
        Toast.makeText(this,p.get(index).getName(),Toast.LENGTH_SHORT).show();
    }
}
