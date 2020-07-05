package com.example.intentchallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivmood,ivphone,ivweb,ivloc;
    Button btn;
    String name,num,loc,web,mood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivmood=findViewById(R.id.ivmood);
        ivphone=findViewById(R.id.ivphone);
        ivloc=findViewById(R.id.ivloc);
        ivweb=findViewById(R.id.ivweb);
        btn=findViewById(R.id.but);
        ivphone.setVisibility(View.GONE);
        ivweb.setVisibility(View.GONE);
        ivloc.setVisibility(View.GONE);
        ivmood.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(MainActivity.this,com.example.intentchallenge.Activitysecond.class);
                startActivityForResult(n,200);
            }
        });
        ivphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num));
                startActivity(a);
            }
        });
        ivweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q="+web));
                startActivity(a);
            }
        });
        ivloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+loc));
                startActivity(a);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200) {
            if (resultCode == RESULT_OK) {
                ivphone.setVisibility(View.VISIBLE);
                ivweb.setVisibility(View.VISIBLE);
                ivloc.setVisibility(View.VISIBLE);
                ivmood.setVisibility(View.VISIBLE);

                name=data.getStringExtra("name");
                num=data.getStringExtra("number");
                web=data.getStringExtra("web");
                loc=data.getStringExtra("loc");
                mood=data.getStringExtra("mood");
                if(mood.equals("happy")){
                    ivmood.setImageResource(R.drawable.happy);
                }
                else if(mood.equals("norm")){
                    ivmood.setImageResource(R.drawable.norm);
                }
                else{
                    ivmood.setImageResource(R.drawable.sad);
                }
            }
            if(resultCode==RESULT_CANCELED){
                Log.d("output","none no data");
            }
        }
    }
}
