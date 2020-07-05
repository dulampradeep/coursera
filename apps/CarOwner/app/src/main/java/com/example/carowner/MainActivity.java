package com.example.carowner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CarAdapter.SendData {

    ImageView cardetailiv;
    TextView cardetailtv,infortv1,infortv2,infortv3;
    Button tocar,touser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardetailiv=findViewById(R.id.cardetailimage);
        cardetailtv=findViewById(R.id.cardetailtv);
        infortv2=findViewById(R.id.nametv);
        infortv1=findViewById(R.id.ownertv);
        infortv3=findViewById(R.id.numbertv);
        tocar=findViewById(R.id.carinfobutton);
        touser=findViewById(R.id.ownerinfobutton);
        sendIndex(0);
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager fr=MainActivity.this.getSupportFragmentManager();
            fr.beginTransaction()
                    .hide(fr.findFragmentById(R.id.fragment2))
                    .hide(fr.findFragmentById(R.id.fragment3))
                    .hide(fr.findFragmentById(R.id.fragment4))
                    .show(fr.findFragmentById(R.id.fragment))
                    .commit();
        }
        else{
            FragmentManager fr=MainActivity.this.getSupportFragmentManager();
            fr.beginTransaction()
                    .show(fr.findFragmentById(R.id.fragment))
                    .show(fr.findFragmentById(R.id.fragment2))
                    .show(fr.findFragmentById(R.id.fragment3))
                    .hide(fr.findFragmentById(R.id.fragment4))
                    .commit();
        }
        tocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.layout_portrait)!=null){
                    FragmentManager fr=MainActivity.this.getSupportFragmentManager();
                    fr.beginTransaction()
                            .hide(fr.findFragmentById(R.id.fragment))
                            .show(fr.findFragmentById(R.id.fragment2))
                            .hide(fr.findFragmentById(R.id.fragment3))
                            .show(fr.findFragmentById(R.id.fragment4))
                            .commit();
                }
                else{
                    FragmentManager fr=MainActivity.this.getSupportFragmentManager();
                    fr.beginTransaction()
                        .show(fr.findFragmentById(R.id.fragment))
                        .show(fr.findFragmentById(R.id.fragment2))
                        .hide(fr.findFragmentById(R.id.fragment3))
                        .show(fr.findFragmentById(R.id.fragment4))
                        .commit();
            }}
        });
        touser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.layout_land)!=null){
                    FragmentManager fr=MainActivity.this.getSupportFragmentManager();
                    fr.beginTransaction()
                            .hide(fr.findFragmentById(R.id.fragment4))
                            .show(fr.findFragmentById(R.id.fragment))
                            .show(fr.findFragmentById(R.id.fragment2))
                            .show(fr.findFragmentById(R.id.fragment3))

                            .commit();
                }
                else {
                    FragmentManager fr = MainActivity.this.getSupportFragmentManager();
                    fr.beginTransaction()
                            .hide(fr.findFragmentById(R.id.fragment))
                            .hide(fr.findFragmentById(R.id.fragment4))
                            .show(fr.findFragmentById(R.id.fragment2))
                            .show(fr.findFragmentById(R.id.fragment3))
                            .commit();
                } }
        });
    }

    @Override
    public void sendIndex(int i) {
        int a[]={R.drawable.mercedes,R.drawable.nissan,R.drawable.volkswagen};
        cardetailiv.setImageResource(a[CarApplication.n.get(i).getI()]);
        cardetailtv.setText(CarApplication.n.get(i).getCarnumber());
        infortv2.setText(CarApplication.n.get(i).getName());
        infortv3.setText(CarApplication.n.get(i).getPhone());
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager fr=MainActivity.this.getSupportFragmentManager();
            fr.beginTransaction()
                    .hide(fr.findFragmentById(R.id.fragment))
                    .hide(fr.findFragmentById(R.id.fragment4))
                    .show(fr.findFragmentById(R.id.fragment2))
                    .show(fr.findFragmentById(R.id.fragment3))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
