package com.example.postboxoriginal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.postboxoriginal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class VerifyMail extends AppCompatActivity {
    Button verifysendcode,newacc;
    EditText verifycode;
    String comingcode;
    String Email;
    TextView showemail;
    ProgressBar prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mail);
        newacc=findViewById(R.id.new_account);
        comingcode=getIntent().getStringExtra("code");
        Email=getIntent().getStringExtra("email");
        showemail=findViewById(R.id.showemail);
        prog=findViewById(R.id.prbar3);
        prog.setVisibility(View.GONE);
        showemail.setVisibility(View.VISIBLE);
        showemail.setText("Email"+Email);
        verifycode=findViewById(R.id.verifycode);
        verifysendcode=findViewById(R.id.verify);
        verifysendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=verifycode.getText().toString().trim();
                if (code.equals(comingcode)){
                    Toast.makeText(VerifyMail.this," Same",Toast.LENGTH_SHORT).show();
                    final FirebaseDatabase fdb=FirebaseDatabase.getInstance();
                    DatabaseReference dbr=fdb.getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +"/").child("emailverification");
                    dbr.setValue(true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(VerifyMail.this,"updated",Toast.LENGTH_SHORT).show();
                                    DatabaseReference st=fdb.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/role");
                                    st.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String p=dataSnapshot.getValue(String.class);
                                            if(p.toLowerCase().equals("student")){
                                                Toast.makeText( VerifyMail.this,p,Toast.LENGTH_SHORT).show();
                                                VerifyMail.this.finish();
                                                Intent n=new Intent(VerifyMail.this,StudentMenu.class);
                                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(n);
                                            }
                                            else{
                                                Toast.makeText( VerifyMail.this,p,Toast.LENGTH_SHORT).show();
                                                VerifyMail.this.finish();
                                                Intent n=new Intent(VerifyMail.this,MainActivity.class);
                                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(n);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            });
                }
                else{
                    Toast.makeText( VerifyMail.this,"Not Same",Toast.LENGTH_SHORT).show();
                }
            }
        });
        newacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                VerifyMail.this.finish();
                Intent n=new Intent(VerifyMail.this,choose_register.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(n);
            }
        });
    }

}
