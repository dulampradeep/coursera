package com.example.savedataintextfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    ArrayList<Person> p;
    EditText ed1,ed2;
    TextView tv1;
    Button b1,b2;
    private final String shared="com.example.savedataintextfiles.share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        p=loadData();

        tv1=findViewById(R.id.textView);
        setTexttv1();
        //b1= findViewById(R.id.but1);
        //b2=findViewById(R.id.but2);
        //SharedPreferences s=getSharedPreferences(shared,MODE_PRIVATE);
        //tv1.setText(s.getString("user","hai"));
        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(ed1.getText().toString().trim()+" "+ed2.getText().toString().trim());
                SharedPreferences.Editor e=getSharedPreferences(shared,MODE_PRIVATE).edit();
                e.putString("user",ed1.getText().toString().trim());
                e.commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }
    public void but1(View v){
        String name=ed1.getText().toString();
        String sur=ed2.getText().toString();
        Person x=new Person(name,sur);
        p.add(x);
        setTexttv1();
    }

    private void setTexttv1() {
        String k="";
        for (Person i:p){
            k+=i.getName()+i.getSur()+"\n";
        }
        tv1.setText(k);
    }

    public void but2(View v){
        try {
            FileOutputStream file=openFileOutput("data.txt",MODE_PRIVATE);
            OutputStreamWriter o=new OutputStreamWriter(file);
            for(Person i:p){
                    o.write(i.getName()+","+i.getSur()+"\n");
            }
            o.flush();
            o.close();
            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){

        }
    }
    public ArrayList<Person> loadData(){
        ArrayList<Person> v = new ArrayList<Person>();

            File file=getApplicationContext().getFileStreamPath("data.txt");
            String line;
            if(file.exists()){
                try{
                    BufferedReader read=new BufferedReader(new InputStreamReader(openFileInput("data.txt")));
                    while((line=read.readLine())!=null){
                        StringTokenizer token=new StringTokenizer(line,",");
                        v.add(new Person(token.nextToken(),token.nextToken()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return v;
    }
}
