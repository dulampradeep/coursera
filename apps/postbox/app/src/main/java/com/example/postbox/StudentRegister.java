package com.example.postbox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentRegister extends AppCompatActivity {
    List l=new ArrayList();
    String item1;
    Spinner s;
    String role;
    TextView head;
    FrameLayout fr;
    public static final String LOGINDETAILS="com.example.postbox.data";
    ProgressBar prog;
    EditText username,name,email,pass,repass,phone,address,sec,year,branch;
    Button cancel,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        prog=findViewById(R.id.prbar21);
        prog.setVisibility(View.GONE);
        username=findViewById(R.id.username1);
        branch=findViewById(R.id.branch);
        name=findViewById(R.id.name1);
        sec=findViewById(R.id.section1);
        year=findViewById(R.id.year1);
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
        /*s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Register.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setVisibility(View.VISIBLE);
               /* Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
                fr.startAnimation(aniFade);*/
                Admin a =fillDetails();
                Backendless.Data.mapTableToClass("People",Admin.class);
                Backendless.Data.of(Admin.class).save(a, new AsyncCallback<Admin>() {
                    @Override
                    public void handleResponse(Admin response) {
                        /*SharedPreferences.Editor sh=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE).edit();
                        sh.putString("id",response.getObjectId());
                        sh.commit();
                        */

                        Toast.makeText(StudentRegister.this,"Registered",Toast.LENGTH_SHORT).show();
                        sendEmailCode(email.getText().toString().trim());
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(StudentRegister.this,"not Registered"+fault.getMessage(),Toast.LENGTH_SHORT).show();
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
                sec.setText("");
                year.setText("");
                branch.setText("");
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
                StudentRegister.this.finish();
                startActivity(sendtoverification);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

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
        a.setBranch(branch.getText().toString().trim());
        a.setSec(sec.getText().toString().trim());
        a.setYear(sec.getText().toString().trim());
        a.setName(name.getText().toString().trim());
        a.setEmail(email.getText().toString().trim());
        a.setAddress(address.getText().toString().trim());
        a.setCollege(s.getSelectedItem().toString().trim());
        a.setPhno(phone.getText().toString().trim());
        a.setPassword(pass.getText().toString().trim());
        a.setUsername(username.getText().toString().trim());
        a.setEmailverification(false);
        a.setRole(role);
        return a;
    }
}
