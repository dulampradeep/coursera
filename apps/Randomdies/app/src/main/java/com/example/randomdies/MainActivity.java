package com.example.randomdies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText ed1;
    TextView tv1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.ed1);
        tv1=findViewById(R.id.textView);
        tv1.setVisibility(View.GONE);
    }
    public void btn(View v){
        int x=(Integer.parseInt(ed1.getText().toString().trim()));
        new backgroundtask().execute(x);
    }



    public class backgroundtask extends AsyncTask<Integer,Integer,String> {
        ProgressDialog p;
        @Override
        protected String doInBackground(Integer... integers) {
            long a=0,b=0,c=0,d=0,e=0,f=0;
            String s;
            Random r=new Random();
            double curr=0;
            double prev=0;
            for(int j=0;j<integers[0];j++){
                curr=(double)j/integers[0];

                if(curr-prev>=0.02){
                    publishProgress(j);
                    prev=curr;
                }
                int p=r.nextInt(6)+1;
                switch(p){

                    case 1:a++;break;
                    case 2:b++;break;
                    case 3:c++;break;
                    case 4:d++;break;
                    case 5:e++;break;
                    case 6:f++;break;

                }

            }
            s="one : "+a+"\ntwo : "+b+"\nthree : "+c+"\nfour : "+d+"\nfive : "+e+"\nsix : "+f;
            return s;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p=new ProgressDialog(MainActivity.this);
            p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            p.setMax(Integer.parseInt(ed1.getText().toString().trim()));
            p.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            p.dismiss();
            tv1.setVisibility(View.VISIBLE);
            tv1.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            p.setProgress(values[0]);
        }
    }
}
