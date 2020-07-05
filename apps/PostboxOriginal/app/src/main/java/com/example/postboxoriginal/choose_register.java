package com.example.postboxoriginal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.example.postboxoriginal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class choose_register extends AppCompatActivity {
    FirebaseAuth mauth;
    List l=new ArrayList();
    String item1;
    public static final String LOGINDETAILS="com.example.postbox.data";
    Spinner s;
    Button login,register;
    String objid;
    Animation aniFade;
    LinearLayout c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_register);
        login=findViewById(R.id.adlogin);
        register=findViewById(R.id.adregis);
        c=findViewById(R.id.tvregisterchoose);
        aniFade= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        c.startAnimation(aniFade);

        s=findViewById(R.id.spinner);
        ActionBar b=getSupportActionBar();
        b.hide();
        l.add(new String("Faculty"));
        l.add(new String("Student"));

        ArrayAdapter adp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,l);
        adp.setDropDownViewResource(R.layout.spinnerdropdowncolor);

        s.setAdapter(adp);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(choose_register.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mauth=FirebaseAuth.getInstance();
        FirebaseUser f=mauth.getCurrentUser();
        if(f!=null) {
            login.setEnabled(false);
            register.setEnabled(false);
            FirebaseDatabase fdb = FirebaseDatabase.getInstance();
            final DatabaseReference st = fdb.getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/role");
            DatabaseReference db = fdb.getReference("users/" + mauth.getCurrentUser().getUid()).child("emailverification");
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean b = dataSnapshot.getValue(Boolean.class);
                    if (b){
                        st.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String p = dataSnapshot.getValue(String.class);
                                if (p.toLowerCase().equals("student")) {
                                    Toast.makeText(choose_register.this, p, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(choose_register.this, StudentMenu.class));
                                    choose_register.this.finish();
                                } else {
                                    Toast.makeText(choose_register.this, p, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(choose_register.this, MainActivity.class));
                                    choose_register.this.finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else{
                        Toast.makeText(choose_register.this,"Need to verify",Toast.LENGTH_SHORT).show();
                        sendEmailCode(mauth.getCurrentUser().getEmail());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else{
            login.setEnabled(true);
            register.setEnabled(true);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1=s.getSelectedItem().toString().trim();
                if(item1.toLowerCase().equals("student")){
                    Intent n=new Intent(choose_register.this,StudentRegister.class);
                    n.putExtra("user",item1);
                    startActivity(n);
                }
                else{
                    Intent n=new Intent(choose_register.this,Register.class);
                    n.putExtra("user",item1);
                    startActivity(n);
            }}
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1=s.getSelectedItem().toString().trim();
                Intent n=new Intent(choose_register.this,Login.class);
                n.putExtra("user",item1);
                startActivity(n);
            }
        });
    }
    protected void sendEmailCode(final String email1){
        final String code=randomgenerator();
        Backendless.Messaging.sendTextEmail("Verification Code By Tornado(pradeep)", "This is the Verification code of your email : " + code, email1, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                Toast.makeText(choose_register.this,"sended to your mail",Toast.LENGTH_SHORT).show();
                Intent sendtoverification=new Intent(choose_register.this,VerifyMail.class);
                sendtoverification.putExtra("email",email1);
                sendtoverification.putExtra("code",code);
                choose_register.this.finish();
                startActivity(sendtoverification);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(choose_register.this, "Sorry server problem"+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected String randomgenerator(){
        Random r=new Random();
        Double d=r.nextDouble()*1000000;
        return Integer.toString(d.intValue());
    }
}
