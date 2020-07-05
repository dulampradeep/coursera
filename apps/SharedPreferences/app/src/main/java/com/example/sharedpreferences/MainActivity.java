package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String FILEH="com.example.sharedpreferences.newfile";
    TextView tv1;
    EditText ed1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        ed1=findViewById(R.id.ed1);
        b1=findViewById(R.id.but);
        SharedPreferences e1=getSharedPreferences(FILEH,MODE_PRIVATE);
        String u=e1.getString("user","");

        tv1.setText(u);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"hai",Toast.LENGTH_SHORT).show();
                tv1.setText(ed1.getText().toString().trim());
                SharedPreferences.Editor e=getSharedPreferences(FILEH,MODE_PRIVATE).edit();
                e.putString("user",ed1.getText().toString().trim());//wehave 20values limit
                e.commit();
            }
        });
    }
}
