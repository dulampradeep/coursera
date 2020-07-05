package com.example.cricketthermometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText e;
    Button b;
    TextView t;
    DecimalFormat d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=findViewById(R.id.ed1);
        b=findViewById(R.id.but);
        t=findViewById(R.id.tv);
        t.setVisibility(View.GONE);
        d=new DecimalFormat("#0.0");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"please enter a number",Toast.LENGTH_SHORT).show();
                }
                else{
                    int x=Integer.parseInt(e.getText().toString().trim());
                    double y=(x/3.0)+4;

                    t.setText("The approximate temp outside is "+d.format(y)+"Degree celcius");
                    t.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
