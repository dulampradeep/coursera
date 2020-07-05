package com.example.otpproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText phone;
    EditText otp;
    Button sub,send;
    String verificationCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks  mcallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();

        mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode=s;
                Toast.makeText(getApplicationContext(),s+"is sent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
        };
        phone=findViewById(R.id.phone);
        otp=findViewById(R.id.otp);
        sub=findViewById(R.id.submit);
        send=findViewById(R.id.done);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }
        );

    }
    public void send_sms(View v){
        String n=phone.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                n,60, TimeUnit.SECONDS,this,mcallback
        );
    }
    public void signInWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"signed in",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void verify(View v){
        String co=otp.getText().toString();
        if(!verificationCode.equals("")){
            verifyPhoneNumber(verificationCode,co);
        }
    }
    public void verifyPhoneNumber(String verifycode,String entered){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifycode,entered);
        signInWithPhone(credential);
    }
}
