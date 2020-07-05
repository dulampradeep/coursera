package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t1);
        Backendless.Data.of("Users").find(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> res) {
                String r = "";
                for (int index = 0; index < res.size(); index++) {
                    Map listItem = res.get(index);

                    Set set = listItem.entrySet();
                    Iterator itr = set.iterator();
                    r+="Email : "+listItem.get("email")+"\n";
                    r+="Phone : "+listItem.get("phone")+"\n";
                    r+="name : "+listItem.get("name")+"\n";
                    r+="password : "+listItem.get("password")+"\n\n\n";
                }
                t1.setText(r);
                Toast.makeText(MainActivity.this,"Searching",Toast.LENGTH_LONG).show();
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this,"Error "+fault.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
