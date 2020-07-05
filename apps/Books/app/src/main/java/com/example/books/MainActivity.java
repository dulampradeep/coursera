package com.example.books;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button all,curr,alreadyred,wishlist,fav,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
    public void allbooks(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }
    public void currbooks(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }
    public void alreadyredbooks(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }
    public void wishlistbooks(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }
    public void favbooks(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }
    public void aboutapp(View view){
        Intent n=new Intent(MainActivity.this,AllBooks.class);
        startActivity(n);
    }

    private void initViews() {
        all=findViewById(R.id.allbooks);
        curr=findViewById(R.id.curredbook);
        alreadyred=findViewById(R.id.alreadyred);
        wishlist=findViewById(R.id.wishlist);
        fav=findViewById(R.id.favorite);
        about=findViewById(R.id.about);
    }
}
