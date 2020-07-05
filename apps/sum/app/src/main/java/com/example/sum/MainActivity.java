package com.example.magicidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sum.R;


public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        but=findViewById(R.id.but);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                int a = Integer.parseInt(ed1.getText().toString().trim());
                int b=Integer.parseInt(ed2.getText().toString().trim());
                ed3.setText(Integer.toString(a+b));
            }
            catch (Exception e){
                Log.d("error",e.getMessage());
            }}
        });

    }
}
