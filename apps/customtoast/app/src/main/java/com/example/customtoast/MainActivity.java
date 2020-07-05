package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("hai pradeep");
            }
        });

    }
    public void showToast(String ter){
        View v=getLayoutInflater().inflate(R.layout.toast,(ViewGroup)findViewById(R.id.taostlay));
        TextView toasttext=v.findViewById(R.id.toasttext);
        toasttext.setText(ter);

        Toast t=new Toast(MainActivity.this);
        t.setDuration(Toast.LENGTH_LONG);
        t.setView(v);
        t.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
        t.show();

    }
}
