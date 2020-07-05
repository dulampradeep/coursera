package com.example.intentchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activitysecond extends AppCompatActivity implements View.OnClickListener {
    EditText etname,etnum,etweb,etloc;
    ImageView iv1,iv2,iv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitysecond);
        etname=findViewById(R.id.etname);
        etnum=findViewById(R.id.etphone);
        etweb=findViewById(R.id.etweb);
        etloc=findViewById(R.id.etadd);
        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(etname.getText().toString().isEmpty()||etnum.getText().toString().isEmpty()||etweb.getText().toString().isEmpty()||etloc.getText().toString().isEmpty()){
            Toast.makeText(Activitysecond.this,"Please fill all the data",Toast.LENGTH_SHORT).show();
        }
        else{
            String name=etname.getText().toString().trim();
            String number=etnum.getText().toString().trim();
            String web=etweb.getText().toString().trim();
            String loc=etloc.getText().toString().trim();

            Intent n=new Intent();
            n.putExtra("name",name);
            n.putExtra("number",number);
            n.putExtra("web",web);
            n.putExtra("loc",loc);
            if(v.getId()==R.id.iv1){
                n.putExtra("mood","happy");
            }
            else if(v.getId()==R.id.iv2){
                n.putExtra("mood","norm");
            }
            else{
                n.putExtra("mood","sad");
            }
            setResult(RESULT_OK,n);
            Activitysecond.this.finish();
        }
    }
}
