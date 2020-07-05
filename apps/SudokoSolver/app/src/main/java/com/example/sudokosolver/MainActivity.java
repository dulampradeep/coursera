package com.example.sudokosolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed[][]=new EditText[9][9];
    Button solve,reset;
    index in[]=new index[81];
    static int k;
    int a[][]=new int[9][9];
    class index{
        int r;
        int c;
        index(int a,int b){
            r=a;
            c=b;
        }
    }
    protected int[] keyFinder(int i,int j){
        int l1=0,l2=0,r1=0,r2=0;
        int x[]=new int[4];
        switch(i){
            case 0:
            case 1:
            case 2:l1=0;l2=2;break;
            case 3:
            case 4:
            case 5:l1=3;l2=5;break;
            case 6:
            case 7:
            case 8:l1=6;l2=8;break;
        }
        switch(j){
            case 0:
            case 1:
            case 2:r1=0;r2=2;break;
            case 3:
            case 4:
            case 5:r1=3;r2=5;break;
            case 6:
            case 7:
            case 8:r1=6;r2=8;break;
        }
        x[0]=l1;
        x[1]=l2;
        x[2]=r1;
        x[3]=r2;
        return x;
    }
    protected boolean cond(int x,int y,int d){
        int r1=0,c1=0,rc1=0;
        for(int i=0;i<9;i++){
            if(a[x][i]==d){
                r1++;
            }
            if(a[i][y]==d){
                c1++;
            }

        }
        int g[]=keyFinder(x,y);
        for(int i=g[0];i<=g[1];i++){
            for(int j=g[2];j<=g[3];j++){
                if(a[i][j]==d){
                    rc1++;
                }
            }
        }
        if(r1==0&&c1==0&&rc1==0){
            return true;
        }
        else{
            return false;
        }
    }
    protected int solve(){
        if(k==-1){
            putData();
            return 1;
        }
        else{
            for(int i=1;i<=9;i++){
                if(cond(in[k].r,in[k].c,i)){
                    a[in[k].r][in[k].c]=i;
                    k--;
                    int pq=solve();
                    if(pq==1) return 1;
                    k++;
                    a[in[k].r][in[k].c]=0;
                }
            }
        }
        return 0;
    }
    protected void  getData(){

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((ed[i][j].getText().toString().trim()).equals("")||(ed[i][j].getText().toString().trim()).equals(null)){
                    a[i][j]=0;
                    in[k]=new index(i,j);
                    k++;
                }
                else{
                    Log.d("bye",ed[i][j].getText().toString().trim());
                    a[i][j]=Integer.parseInt(ed[i][j].getText().toString().trim());
                }

            }
        }k--;

    }
    protected void putData(){
        try{
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                ed[i][j].setText(Integer.toString(a[i][j]));
            }
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void clear(){
        k=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                ed[i][j].setText("");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String p;
        solve=findViewById(R.id.solve);
        reset=findViewById(R.id.clear);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                p="tv"+Integer.toString(i)+Integer.toString(j);
                int ff=getResources().getIdentifier(p, "id", getPackageName());
                ed[i][j]=(EditText) findViewById(ff);

            }
        }
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

                   int k= solve();
                if(k==1)
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });



    }
}
