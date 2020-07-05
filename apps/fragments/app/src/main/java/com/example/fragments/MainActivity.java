package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItSelected {
    TextView tvdet;
    //ArrayList<String> des;
    String [] des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvdet=findViewById(R.id.tvdet);
        //des=new ArrayList<String>();
        des=getResources().getStringArray(R.array.descriptions);
        /*des.add("Desc of item 1");
        des.add("Desc of item 2");
        des.add("Desc of item 3");*/
        //In potrait mode
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager m=this.getSupportFragmentManager();
            m.beginTransaction()
                    .hide(m.findFragmentById(R.id.detailfrag))
                    .show(m.findFragmentById(R.id.listfrag))
                    .commit();
        }
        //in landscape mode
        if(findViewById(R.id.layout_land)!=null){
            FragmentManager m=this.getSupportFragmentManager();
            m.beginTransaction()
                    .show(m.findFragmentById(R.id.detailfrag))
                    .show(m.findFragmentById(R.id.listfrag))
                    .commit();
        }
    }

    @Override
    public void getSelected(int index) {
        tvdet.setText(des[index]);
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager m=this.getSupportFragmentManager();
            m.beginTransaction()
                    .show(m.findFragmentById(R.id.detailfrag))
                    .hide(m.findFragmentById(R.id.listfrag))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
