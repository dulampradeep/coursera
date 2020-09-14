package com.example.postboxoriginal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;

import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telecom.Call;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class chattingactivity extends AppCompatActivity {
    TextView chatname,chatstatus;
    CircleImageView chatprofile;
    EditText chatedittext;
    ImageButton chatsend,camerasend;
    ImageView back;
    MaterialToolbar toolbar1;
    RecyclerView rv;
    private static final int  CAMERA_REQUEST_CODE=526;
    RecyclerView.Adapter adp;
    RecyclerView.LayoutManager lm;
    String curruser;
    FirebaseDatabase fd;
    DatabaseReference dbr,hr,rdc,countupdate;
    Bundle b;
    ArrayList<Messagesclass> l;
    String receiver;
    int j=0;
    private StorageReference mstorageref;

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
        camerasend=findViewById(R.id.camerasend);
        curruser=FirebaseAuth.getInstance().getCurrentUser().getUid();
        fd=FirebaseDatabase.getInstance();
        mstorageref= FirebaseStorage.getInstance().getReference();

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
        final MediaPlayer mp=MediaPlayer.create(chattingactivity.this,R.raw.throughteeth);
        mp.setVolume(0.00f,0.06f);

        dbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Messagesclass g=dataSnapshot.getValue(Messagesclass.class);

                if((g.getSender().equals(curruser) && g.getReceiver().equals(receiver))||(g.getReceiver().equals(curruser) && g.getSender().equals(receiver))) {
                    Messagesclass mnull=new Messagesclass();
                    mnull.setMessage(null);
                    if(l.size()!=0){
                        l.remove(l.size()-1);
                        l.remove(l.size()-1);
                    }
                    l.add(g);
                    l.add(mnull);
                    l.add(mnull);
                    adp=new MessagesShowAdapter(chattingactivity.this,l,b.getString("profilepic"));
                    rv.setAdapter(adp);
                    //rv.scrollToPosition(l.size()-1);
                    if(rv.getAdapter().getItemCount()!=0) {
                        rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
                    }
                    adp.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                l.clear();
                for(DataSnapshot i :dataSnapshot.getChildren()){
                    Messagesclass g=i.getValue(Messagesclass.class);
                    if((g.getSender().equals(curruser) && g.getReceiver().equals(receiver))||(g.getReceiver().equals(curruser) && g.getSender().equals(receiver))){
                        l.add(g);
                    }
                }
                Messagesclass mnull=new Messagesclass();
                mnull.setMessage(null);
                l.add(mnull);
                Messagesclass mnull1=new Messagesclass();
                mnull1.setMessage(null);
                l.add(mnull1);


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
        });*/

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


        camerasend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((ActivityCompat.checkSelfPermission(chattingactivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) ||(ActivityCompat.checkSelfPermission(chattingactivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(chattingactivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
                }
                else{
                    Toast.makeText(chattingactivity.this, "firstGranted", Toast.LENGTH_SHORT).show();
                    pickfromcamera();
                }
            }
        });

        chatsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatedittext.getText().toString().trim().length() !=0){
                    hr.setValue("online");
                    mp.start();

                    Messagesclass m=new Messagesclass();
                    m.setType("text");
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

    private void saveToBackendless(Uri b){
        Messagesclass m=new Messagesclass();
        final String id=dbr.push().getKey();
        final StorageReference st=mstorageref.child("Photos/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+id+"/");
        final UploadTask uptask=st.putFile(b);
        final MediaPlayer mp=MediaPlayer.create(chattingactivity.this,R.raw.throughteeth);
        mp.setVolume(0.00f,0.06f);

        uptask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                st.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //FirebaseDatabase fdb=FirebaseDatabase.getInstance();
//                        DatabaseReference st=fdb.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profilepic");
//                        st.setValue(uri.toString());
//                        hr.setValue("online");
                        Toast.makeText(chattingactivity.this,uri.toString(),Toast.LENGTH_SHORT).show();
                        mp.start();
                        Messagesclass m=new Messagesclass();
                        m.setType("image");
                        m.setMessage(uri.toString());
                        m.setSender(curruser);
                        m.setReceiver(receiver);
                        m.setStatus("unseen");
                        m.setTimestamp(String.valueOf(System.currentTimeMillis()));
                        m.setMessageid(id);
                        dbr.child(id).setValue(m);
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

                });
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                saveToBackendless(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        /*if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                saveToBackendless(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            Bundle b3=data.getExtras();
            try {
                Bitmap b2=(Bitmap) b3.get("data");
                Uri b1 = getImageUri(chattingactivity.this,b2);
                CropImage.activity(b1)
                        .start(this);

                Toast.makeText(chattingactivity.this,b1.toString(),Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(chattingactivity.this,e.getMessage()+"in activita ",Toast.LENGTH_SHORT).show();
            }
        }*/
    }
    /*private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (101 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               pickfromcamera();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(chattingactivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder dia = new AlertDialog.Builder(this);
                    dia.setMessage("this is important")
                            .setTitle("Required");
                    dia.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(chattingactivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                        }
                    });
                    dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(chattingactivity.this, "waste not Granted", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(chattingactivity.this, "Never showed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void pickfromcamera() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(chattingactivity.this);
    }
}
    //ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_REORDER,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,150);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE,150);
//        toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE,400);
//        toneGen1.startTone(ToneGenerator.TONE_CDMA_ANSWER,150);
