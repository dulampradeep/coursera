package com.example.tornado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person p=new Person();
        p.setName("pradeep");
        p.setPass("pradeep");
        Backendless.Data.of(Person.class).save(p, new AsyncCallback<Person>() {
            @Override
            public void handleResponse(Person response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_LONG);
            }
        });
        Backendless.Data.of(Person.class).getObjectCount(new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer response) {
                Toast.makeText(MainActivity.this,Integer.toString(response),Toast.LENGTH_LONG);
                Log.d("objects",Integer.toString(response));
                Intent n=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(n);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
