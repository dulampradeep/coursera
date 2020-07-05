package com.example.postboxoriginal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.example.postboxoriginal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    List l=new ArrayList();
    List branch,year,sec;

    Spinner s,yearspinner,secspinner,branchspinner;
    RadioGroup rg;
    RadioButton rb;
    String item1;

    String role;
    TextView head;
    FrameLayout fr;
    ProgressBar prog;
    public static final String LOGINDETAILS="com.example.postbox.data";
    EditText username,name,email,pass,repass,phone,address;
    Button cancel,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        Toast.makeText(this,FirebaseAuth.getInstance().getUid(),Toast.LENGTH_SHORT).show();

        rg=findViewById(R.id.gedergroup1);


        branch=new ArrayList();
        sec=new ArrayList();
        year=new ArrayList();
        branch.add("NONE");
        branch.add("CSE");
        branch.add("ECE");
        branch.add("EEE");
        branch.add("IT");
        branch.add("CIVIL");
        sec.add("NONE");
        sec.add("A");
        sec.add("B");
        sec.add("C");
        year.add("NONE");
        year.add("First");
        year.add("Second");
        year.add("Third");
        year.add("Fourth");
        prog=findViewById(R.id.prbar2);
        prog.setVisibility(View.GONE);



        yearspinner=findViewById(R.id.yearspinner1);
        secspinner=findViewById(R.id.sectionspinner1);
        branchspinner=findViewById(R.id.branchspinner1);


        prog.setVisibility(View.GONE);
        username=findViewById(R.id.username);
        name=findViewById(R.id.name);
        email=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        repass=findViewById(R.id.repassword);
        fr=findViewById(R.id.forfading);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        cancel=findViewById(R.id.cancel);
        submit=findViewById(R.id.register);

        ActionBar b=getSupportActionBar();
        b.hide();
        head=findViewById(R.id.textView9);

        l.add(new String("Scet"));
        l.add(new String("Siet"));
        l.add(new String("swrn"));
        String s1=getIntent().getStringExtra("user").trim();
        String s2=s1.substring(0,1).toUpperCase()+s1.substring(1).toLowerCase();
        s2+=" "+head.getText();
        head.setText(s2);
        role=s1.toLowerCase();
        ArrayAdapter adp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,l);
        adp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        s=findViewById(R.id.spinner2);
        s.setAdapter(adp);
        ArrayAdapter secadp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,sec);
        secadp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        secspinner.setAdapter(secadp);

        ArrayAdapter yearadp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,year);
        yearadp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        yearspinner.setAdapter(yearadp);

        ArrayAdapter branchadp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,branch);
        branchadp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        branchspinner.setAdapter(branchadp);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setVisibility(View.VISIBLE);
                rb=findViewById(rg.getCheckedRadioButtonId());
                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user=mAuth.getCurrentUser();
                                    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
                                    DatabaseReference dbr=fdb.getReference("users");
                                    Admin j=fillDetails();
                                    DatabaseReference dbr12=fdb.getReference("notifications").child(j.getUserId());
                                    dbr12.setValue("Notifications");
                                    dbr.child(j.getUserId()).setValue(j);
                                    sendEmailCode(j.getEmail());
                                }
                                else{
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                name.setText("");
                email.setText("");
                address.setText("");
                phone.setText("");
                pass.setText("");
                repass.setText("");
            }
        });
    }
    protected void sendEmailCode(final String email1){
        final String code=randomgenerator();
        Backendless.Messaging.sendTextEmail("Verification Code By Tornado(pradeep)", "This is the Verification code of your email : " + code, email1, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                Toast.makeText(Register.this,"sended to your mail",Toast.LENGTH_SHORT).show();
                Intent sendtoverification=new Intent(Register.this,VerifyMail.class);
                sendtoverification.putExtra("email",email1);
                sendtoverification.putExtra("code",code);
                startActivity(sendtoverification);
                Register.this.finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(Register.this,"sended to your mail",Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected String randomgenerator(){
        Random r=new Random();
        Double d=r.nextDouble()*1000000;
        return Integer.toString(d.intValue());
    }
    protected Admin fillDetails(){
        Admin a=new Admin();
        a.setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        a.setProfilepic("");
        a.setBranch(branchspinner.getSelectedItem().toString().trim());
        a.setSec(secspinner.getSelectedItem().toString().trim());
        a.setYear(yearspinner.getSelectedItem().toString().trim());
        a.setName(name.getText().toString().trim());
        a.setEmail(email.getText().toString().trim());
        a.setAddress(address.getText().toString().trim());
        a.setCollege(s.getSelectedItem().toString().trim());
        a.setPhno(phone.getText().toString().trim());
        a.setPassword(pass.getText().toString().trim());
        a.setUsername(username.getText().toString().trim());
        a.setEmailverification(false);
        a.setRole(role);
        a.setGender(rb.getText().toString().trim());
        return a;
    }

}
