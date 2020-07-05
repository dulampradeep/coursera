package com.example.cardlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ClickedItem {
    RecyclerView rv;
    RecyclerView.Adapter rvad;
    RecyclerView.LayoutManager lm;
    ArrayList<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.list);
        rv.setHasFixedSize(true);

        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        people=new ArrayList<Person>();
        people.add(new Person("pradeep","dulam","bus"));
        people.add(new Person("prad","dulajom","bus"));
        people.add(new Person("prade","dulalojm","flight"));
        people.add(new Person("preep","dlam","flight"));
        people.add(new Person("prep","dulamolp","flight"));
        people.add(new Person("prap","duam","bus"));
        people.add(new Person("deep","dum","flight"));
        people.add(new Person("pradeep","dulam","bus"));
        people.add(new Person("prad","dulajom","bus"));
        people.add(new Person("prade","dulalojm","flight"));
        people.add(new Person("preep","dlam","flight"));
        people.add(new Person("prep","dulamolp","flight"));
        people.add(new Person("prap","duam","bus"));
        people.add(new Person("deep","dum","flight"));

        rvad=new PersonAdapter(MainActivity.this,people);
        rv.setAdapter(rvad);

    }

    @Override
    public void getClicked(int index) {
        Toast.makeText(MainActivity.this,people.get(index).getName(),Toast.LENGTH_SHORT).show();
    }
}
