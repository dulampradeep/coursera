package com.example.postboxoriginal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class chattingactivity extends AppCompatActivity {
    TextView chatname,chatstatus;
    CircleImageView chatprofile;
    EditText chatedittext;
    ImageButton chatsend;
    ImageView back;
    MaterialToolbar toolbar1;
    RecyclerView rv;
    RecyclerView.Adapter adp;
    RecyclerView.LayoutManager lm;
    String curruser;
    FirebaseDatabase fd;
    DatabaseReference dbr,hr,rdc,countupdate;
    Bundle b;
    ArrayList<Messagesclass> l;
    String receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattingactivity);
        l=new ArrayList<>();
        toolbar1=findViewById(R.id.materialtoolbar);
        setSupportActionBar(toolbar1);
        back=findViewById(R.id.back);
        rv=findViewById(R.id.messagesrv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(chattingactivity.this));
        chatprofile=findViewById(R.id.chatprofile);
        chatedittext=findViewById(R.id.chatedittext);
        chatsend=findViewById(R.id.chatsendmessage);
        chatname=findViewById(R.id.chatname);
        chatstatus=findViewById(R.id.chatstatus);
        curruser=FirebaseAuth.getInstance().getCurrentUser().getUid();
        fd=FirebaseDatabase.getInstance();


        b=getIntent().getBundleExtra("bundle");
        if(b!=null){
            String name=b.getString("name");
            if(name!=null){
                chatname.setText(name);
            }
            String profile1=b.getString("profilepic");
            if(profile1!=null){
                Picasso.get().load(Uri.parse(profile1))
                        .placeholder(R.drawable.default_profile)
                        .into(chatprofile);
            }
            String recei=b.getString("receiver");
            if(recei!=null){
                receiver=recei;
            }
        }
        countupdate=fd.getReference().child("users").child(receiver);
        hr=fd.getReference("users").child(curruser).child("status");
        hr.setValue("online");
        dbr=fd.getReference("messages");
        rdc=fd.getReference("users").child(receiver).child("status");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                l.clear();
                for(DataSnapshot i :dataSnapshot.getChildren()){
                    Messagesclass g=i.getValue(Messagesclass.class);
                    if((g.getSender().equals(curruser) && g.getReceiver().equals(receiver))||(g.getReceiver().equals(curruser)&&g.getSender().equals(receiver))){
                        l.add(g);
                    }
                }
                adp=new MessagesShowAdapter(chattingactivity.this,l,b.getString("profilepic"));
                rv.setAdapter(adp);
                //rv.scrollToPosition(l.size()-1);
                if(rv.getAdapter().getItemCount()!=0) {
                    rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rdc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(String.class)!=null){
                if(dataSnapshot.getValue(String.class).equals("typing...")){
                    chatstatus.setText("typing...");
                }
                else if(dataSnapshot.getValue(String.class).equals("online")){
                    chatstatus.setText("Online");
                }
                else{
                    chatstatus.setText("Offline");
                }
            }
                else{
                    chatstatus.setText("Not Available");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hr.setValue("offline");
                finish();
            }
        });



        chatsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatedittext.getText().toString().trim().length() !=0){
                    hr.setValue("online");
                    Toast.makeText(chattingactivity.this,"pressed",Toast.LENGTH_SHORT).show();
                    Messagesclass m=new Messagesclass();
                    m.setMessage(chatedittext.getText().toString().trim());
                    m.setSender(curruser);
                    m.setReceiver(receiver);
                    m.setStatus("unseen");
                    m.setTimestamp(String.valueOf(System.currentTimeMillis()));
                    String id=dbr.push().getKey();
                    m.setMessageid(id);
                    dbr.child(id).setValue(m);
                    chatedittext.setText("");

                   countupdate.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Admin a=dataSnapshot.getValue(Admin.class);
                           if(a.getMessagescount()==null){
                               MessagesCount m=new MessagesCount();
                               m.setCount(1);
                               m.setUserid(curruser);
                               Map<String,MessagesCount> hm=new HashMap<>();
                               hm.put(curruser,m);
                               a.setMessagescount(hm);
                               countupdate.setValue(a);
                           }
                           else if(a.getMessagescount().containsKey(curruser)==false){
                               MessagesCount m=new MessagesCount();
                               m.setCount(1);
                               m.setUserid(curruser);
                               Map<String,MessagesCount> hm=a.getMessagescount();
                               hm.put(curruser,m);
                               a.setMessagescount(hm);
                               countupdate.setValue(a);
                           }
                           else{
                              Admin p=dataSnapshot.getValue(Admin.class);
                              MessagesCount m13=p.getMessagescount().get(curruser);
                               m13.setCount(m13.getCount()+1);
                               Map<String,MessagesCount> hm1=p.getMessagescount();
                               hm1.put(curruser,m13);
                              p.setMessagescount(hm1);
                              countupdate.setValue(p);
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                }
            }
        });
        chatedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0){
                    hr.setValue("typing...");
            }
                else{
                    hr.setValue("online");
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.grpsettings:
                Toast.makeText(chattingactivity.this,"hai",Toast.LENGTH_SHORT).show();break;
            case R.id.clearchat:
                Toast.makeText(chattingactivity.this,"wait bro",Toast.LENGTH_SHORT).show();break;

            default:
                Toast.makeText(chattingactivity.this,"defaulted",Toast.LENGTH_SHORT).show();break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m= getMenuInflater();
        m.inflate(R.menu.chattingmenu,menu);
        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        hr.setValue("offline");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hr.setValue("online");
    }
}
