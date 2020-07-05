package com.example.postbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.backendless.persistence.BackendlessDataCollection;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.local.UserIdStorageFactory;

import java.util.List;
import java.util.Random;

public class Login extends AppCompatActivity {
    public static final String LOGINDETAILS="com.example.postbox.data";
    EditText email,pass;
    Button login;
    String role;
    TextView tv;
    String l1,l2;
    FrameLayout c;
    Animation aniFade;
    ProgressBar prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prog=findViewById(R.id.prbar);
        prog.setVisibility(View.GONE);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);

        login=findViewById(R.id.login);
        c=findViewById(R.id.forfadeing);
        aniFade= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        c.startAnimation(aniFade);
        role=getIntent().getStringExtra("user");
        tv=findViewById(R.id.textView9);
        tv.setText(role+" Login");
        SharedPreferences shar=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        l1=shar.getString("mail","");
        l2=shar.getString("pass","");
        if(l1!="" && l2!=""){
            loginclick(login);
            login.setEnabled(false);
            aniFade.cancel();
        }
    }
   public void loginclick(View v){
       /*Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
       c.startAnimation(aniFade);*/
       prog.setVisibility(View.VISIBLE);
        final String e,p;
        if(l1!="" && l2!=""){
            e=l1;
            p=l2;
        }else {
            login.setEnabled(true);
            aniFade.cancel();

            e = email.getText().toString().trim();
            p = pass.getText().toString().trim();
        }
        if(e.isEmpty() ||p.isEmpty()){
            Toast.makeText(Login.this,"Please Enter Both",Toast.LENGTH_SHORT).show();
            prog.setVisibility(View.GONE);
        }
        else{
            DataQueryBuilder builder =DataQueryBuilder.create();
            builder.setWhereClause("email ='"+e+"' and password = '"+p+"' and role='"+role+"'");
            Backendless.Data.mapTableToClass("People",Admin.class);
            Backendless.Data.of(Admin.class).find(builder, new AsyncCallback<List<Admin>>() {
                @Override
                public void handleResponse(List<Admin> response) {
                    if(response.isEmpty()){
                        prog.setVisibility(View.GONE);
                        Toast.makeText(Login.this,"please Enter a valid Email or password",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        prog.setVisibility(View.GONE);
                        if(!response.get(0).isEmailverification()){
                            Toast.makeText(Login.this,"Need to verify mail \n"+response.get(0).getName(),Toast.LENGTH_SHORT).show();
                            sendEmailCode(e);
                        }
                        else{
                            SharedPreferences.Editor logindata=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE).edit();
                            logindata.putString("mail",e);
                            logindata.putString("pass",p);
                            logindata.putString("role",role);
                            logindata.putString("id",response.get(0).getObjectId());
                            logindata.commit();
                            Toast.makeText(Login.this,response.get(0).getObjectId()+role,Toast.LENGTH_SHORT).show();
                            prog.setVisibility(View.GONE);
                            if(role.toLowerCase().equals("student")){
                               Intent n=new Intent(Login.this,StudentMenu.class);
                                startActivity(n);
                            }
                            else{
                               Intent n=new Intent(Login.this,MainActivity.class);
                                startActivity(n);
                            }

                            Login.this.finish();
                        }


                    }}

                @Override
                public void handleFault(BackendlessFault fault) {
                    prog.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"data Is \n"+fault.getMessage(),Toast.LENGTH_SHORT).show();
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

}
