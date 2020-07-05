package com.example.postbox;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class VerifyMail extends AppCompatActivity {
    Button verifysendcode;
    EditText verifycode;
    String comingcode;
    String Email;
    TextView showemail;
    ProgressBar prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comingcode=getIntent().getStringExtra("code");
        Email=getIntent().getStringExtra("email");
        setContentView(R.layout.activity_verify_mail);
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
                    Map<String,Object> m=new HashMap<>();
                    m.put("emailverification",true);
                    Backendless.Data.of(Admin.class).update("email='"+Email+"'", m, new AsyncCallback<Integer>() {
                        @Override
                        public void handleResponse(Integer response) {
                            Toast.makeText(VerifyMail.this,"Updated ",Toast.LENGTH_SHORT).show();
                            VerifyMail.this.finish();
                            prog.setVisibility(View.VISIBLE);
                            startActivity(new Intent(VerifyMail.this,choose_register.class));

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(VerifyMail.this,"not Updated "+fault.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText( VerifyMail.this,"Not Same",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
