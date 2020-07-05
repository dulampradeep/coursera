package com.example.postboxoriginal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.backendless.persistence.DataQueryBuilder;
import com.example.postboxoriginal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;

public class Login extends AppCompatActivity {
    public static final String LOGINDETAILS="com.example.postbox.data";
    EditText email,pass;
    Button login;
    String role;
    TextView tv;
    String l1,l2;
    private FirebaseAuth mauth;
    FrameLayout c;
    Animation aniFade;
    ProgressBar prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth=FirebaseAuth.getInstance();
        prog=findViewById(R.id.prbar);
        prog.setVisibility(View.GONE);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);

        login=findViewById(R.id.login);
        c=findViewById(R.id.forfadeing);
        aniFade= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);

        role=getIntent().getStringExtra("user");
        tv=findViewById(R.id.textView9);
        tv.setText("User Login");
        SharedPreferences shar=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        l1=shar.getString("mail","");
        l2=shar.getString("pass","");

    }
   public void loginclick(View v){
       login.setEnabled(false);
       final FirebaseDatabase fd=FirebaseDatabase.getInstance();
       if(email.getText().toString()==null ||pass.getText().toString()==null||email.getText().toString().trim().toString().equals("") ||pass.getText().toString().equals("")){
           Toast.makeText(Login.this,"please fill ",Toast.LENGTH_SHORT).show();
           login.setEnabled(true);
       }
       else {
           c.startAnimation(aniFade);
           mauth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                   .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               if (mauth.getCurrentUser() != null) {
                                   DatabaseReference db = fd.getReference("users/" + mauth.getCurrentUser().getUid()).child("emailverification");
                                   db.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                           boolean b = dataSnapshot.getValue(Boolean.class);
                                           if (b==true) {
                                               updateUI(mauth.getCurrentUser());
                                           } else {
                                               Toast.makeText(Login.this, "Need to verify", Toast.LENGTH_SHORT).show();
                                               sendEmailCode(mauth.getCurrentUser().getEmail());
                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });
                               } else {
                                   Toast.makeText(Login.this, "Need to register", Toast.LENGTH_SHORT).show();
                               }
                           } else {
                               login.setEnabled(true);

                               Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
       }
    }
    protected void sendEmailCode(final String email1){
        final String code=randomgenerator();
        Backendless.Messaging.sendTextEmail("Verification Code By Tornado(pradeep)", "This is the Verification code of your email : " + code, email1, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                Toast.makeText(Login.this,"sended to your mail",Toast.LENGTH_SHORT).show();
                Intent sendtoverification=new Intent(Login.this,VerifyMail.class);
                sendtoverification.putExtra("email",email1);
                sendtoverification.putExtra("code",code);
                Login.this.finish();
                startActivity(sendtoverification);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(Login.this, "Sorry server problem"+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected String randomgenerator(){
        Random r=new Random();
        Double d=r.nextDouble()*1000000;
        return Integer.toString(d.intValue());
    }
    public void updateUI(FirebaseUser u){
        if(u!=null){
            FirebaseDatabase fdb=FirebaseDatabase.getInstance();
            DatabaseReference st=fdb.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/role");
            st.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String p=dataSnapshot.getValue(String.class);
                    if(p.toLowerCase().equals("student")){
                        Toast.makeText( Login.this,p,Toast.LENGTH_SHORT).show();
                        Login.this.finish();
                        startActivity(new Intent(Login.this,StudentMenu.class));
                    }
                    else{
                        Toast.makeText( Login.this,p,Toast.LENGTH_SHORT).show();
                        Login.this.finish();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else{
            login.setEnabled(true);
            Toast.makeText(Login.this,"Need to register",Toast.LENGTH_SHORT).show();
        }
    }
}
