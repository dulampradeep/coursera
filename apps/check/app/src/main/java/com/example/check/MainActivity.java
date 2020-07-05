package com.example.check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText l1,l2;
    Button b1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.but);
        l1=findViewById(R.id.ed1);
        l2=findViewById(R.id.ed2);
        t1=findViewById(R.id.tv2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(l1.getText().toString().trim());
                int b=Integer.parseInt(l2.getText().toString().trim());
                t1.setText(Integer.toString(a+b));
            }
        });
    }
}
