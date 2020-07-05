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
import com.google.android.gms.tasks.OnCompleteListener;
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


public class StudentRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    List l=new ArrayList();
    List branch,year,sec;

    Spinner s,yearspinner,secspinner,branchspinner;
    String role;
    TextView head;
    RadioGroup rg;
    RadioButton rb;
    FrameLayout fr;
    public static final String LOGINDETAILS="com.example.postbox.data";
    ProgressBar prog;
    EditText username,name,email,pass,repass,phone,address;
    Button cancel,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        Toast.makeText(this,FirebaseAuth.getInstance().getUid(),Toast.LENGTH_SHORT).show();

        rg=findViewById(R.id.gedergroup);

        branch=new ArrayList();
        sec=new ArrayList();
        year=new ArrayList();
        branch.add("CSE");
        branch.add("ECE");
        branch.add("EEE");
        branch.add("IT");
        branch.add("CIVIL");
        sec.add("A");
        sec.add("B");
        sec.add("C");
        year.add("First");
        year.add("Second");
        year.add("Third");
        year.add("Fourth");
        prog=findViewById(R.id.prbar21);
        prog.setVisibility(View.GONE);
        username=findViewById(R.id.username1);

        name=findViewById(R.id.name1);
        yearspinner=findViewById(R.id.yearspinner);
        secspinner=findViewById(R.id.sectionspinner);
        branchspinner=findViewById(R.id.branchspinner);

        email=findViewById(R.id.mail1);
        pass=findViewById(R.id.password1);
        repass=findViewById(R.id.repassword1);
        fr=findViewById(R.id.forfading1);
        phone=findViewById(R.id.phone1);
        address=findViewById(R.id.address1);
        cancel=findViewById(R.id.cancel1);
        submit=findViewById(R.id.register1);

        ActionBar b=getSupportActionBar();
        b.hide();
        head=findViewById(R.id.textView91);

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
        s=findViewById(R.id.spinner21);
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
                        .addOnCompleteListener(StudentRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     FirebaseUser user=mAuth.getCurrentUser();
                                     FirebaseDatabase fdb=FirebaseDatabase.getInstance();
                                     DatabaseReference dbr=fdb.getReference("users");
                                     Admin j=fillDetails();
                                     DatabaseReference dbr1=fdb.getReference("notifications").child(j.getUserId());
                                     dbr1.setValue("Notifications");
                                     dbr.child(j.getUserId()).setValue(j);
                                     sendEmailCode(j.getEmail());
                                 }
                                 else{
                                     Toast.makeText(StudentRegister.this, "Authentication failed.",
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
                Toast.makeText(StudentRegister.this,"sended to your mail",Toast.LENGTH_SHORT).show();
                Intent sendtoverification=new Intent(StudentRegister.this,VerifyMail.class);
                sendtoverification.putExtra("email",email1);
                sendtoverification.putExtra("code",code);
                startActivity(sendtoverification);
                StudentRegister.this.finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(StudentRegister.this,"sended to your mail",Toast.LENGTH_SHORT).show();
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
        a.setProfilepic("");
        a.setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
