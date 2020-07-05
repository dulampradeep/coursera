package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvname,tvnumber;
    EditText etname,etnumber;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvname=findViewById(R.id.tvname);
        tvnumber=findViewById(R.id.tvphone);
        etname=findViewById(R.id.etname);
        etnumber=findViewById(R.id.etphone);
        but=findViewById(R.id.butsub);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
