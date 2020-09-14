package com.example.postboxoriginal;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.example.postboxoriginal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NotificationBadge{
    private DrawerLayout drawer;
    private FirebaseAuth mauth;
    private StorageReference mstorageref;
    private static final int CODE=123;
    ProgressBar prbar2;
    String nam;
    NavigationView navigation;
    TextView notibadge1;
    String id;
    int count1,count2;
    TextView headerusername,headermail;
    private CircleImageView profilepic;
    public static final String LOGINDETAILS="com.example.postbox.data";
    @Override
    public void getcounts(int count1) {
        this.count1=count1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_menu);
        mauth= FirebaseAuth.getInstance();
        SharedPreferences sha=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        nam=sha.getString("mail","");
        id=sha.getString("id","");
        try {
            navigation =findViewById(R.id.nav_view1);
            notibadge1 = (TextView) MenuItemCompat.getActionView(navigation.getMenu().findItem(R.id.notifications));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        initializeCountDrawer();

        mstorageref= FirebaseStorage.getInstance().getReference();
        profilepic=findViewById(R.id.profilepic);

        Toolbar t=findViewById(R.id.toolbar1);
        setSupportActionBar(t);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle a=new ActionBarDrawerToggle(this,drawer,t,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(a);
        NavigationView n=findViewById(R.id.nav_view1);
        n.setNavigationItemSelectedListener(this);
        a.syncState();
        final View headerview=n.getHeaderView(0);
        prbar2=findViewById(R.id.prbar2);
        prbar2.setVisibility(View.GONE);
        profilepic=headerview.findViewById(R.id.profilepic);
        headerusername=headerview.findViewById(R.id.headerusername);
        headermail=headerview.findViewById(R.id.headermail);

        FirebaseDatabase fdb=FirebaseDatabase.getInstance();
        DatabaseReference st=fdb.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/");
        st.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Admin s = dataSnapshot.getValue(Admin.class);
                headerusername.setText(s.getUsername());
                headermail.setText(s.getEmail());
                    Picasso.get().load(Uri.parse(s.getProfilepic()))
                            .noFade()
                            .error(R.drawable.default_profile)
                            .placeholder(R.drawable.default_profile)
                            .into(profilepic);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new TeacherDetails() ).commit();
            n.setCheckedItem(R.id.navchat1);
        }
        //startActivity(new Intent(MainActivity.this,MapsPrad.class));

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ActivityCompat.checkSelfPermission(StudentMenu.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(StudentMenu.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(StudentMenu.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE);
                } else {
                    Toast.makeText(StudentMenu.this, "firstGranted", Toast.LENGTH_LONG).show();

                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(StudentMenu.this);
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new NotifiesFragment()).commit();
                break;
            case R.id.navchat1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new TeacherDetails()).commit();
                break;
            case R.id.techdetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new Chat1Fragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new getProfile()).commit();
                break;
            case R.id.setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new Chat1Fragment()).commit();
                break;
            case R.id.send:Toast.makeText(this,"send",Toast.LENGTH_SHORT).show();break;
            case R.id.logout:
                FirebaseUser user = mauth.getCurrentUser();
                if (user != null){
                    mauth.signOut();
                    //Toast.makeText(this, user.getEmail()+ " Sign out!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentMenu.this,choose_register.class));
                    StudentMenu.this.finish();
                }else{
                    Toast.makeText(this, "You aren't login Yet!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (CODE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(StudentMenu.this);
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(StudentMenu.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder dia = new AlertDialog.Builder(this);
                    dia.setMessage("this is important")
                            .setTitle("Required");
                    dia.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(StudentMenu.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE);
                        }
                    });
                    dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(StudentMenu.this, "waste not Granted", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(StudentMenu.this, "Never showed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private String saveToBackendless(Uri b){
        prbar2.setVisibility(View.VISIBLE);
        final StorageReference st=mstorageref.child("profiles/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/");
        final UploadTask uptask=st.putFile(b);
        uptask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                st.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseDatabase fdb=FirebaseDatabase.getInstance();
                        DatabaseReference st=fdb.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profilepic");
                        st.setValue(uri.toString());
                        Picasso.get().load(uri)
                                .noFade()
                                .error(R.drawable.default_profile)
                                .placeholder(R.drawable.default_profile)
                                .into(profilepic);
                        prbar2.setVisibility(View.GONE);
                    }

                });


            }
        });

        return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                saveToBackendless(resultUri);
                prbar2.setVisibility(View.VISIBLE);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                prbar2.setVisibility(View.GONE);
            }
        }
    }
    private void initializeCountDrawer() {

            final List<NotificationClass> l = new ArrayList<>();
            FirebaseDatabase fd = FirebaseDatabase.getInstance();
            DatabaseReference dbr = fd.getReference("notifications").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            dbr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (navigation.getCheckedItem().getItemId() != R.id.notifications) {
                        notibadge1.setVisibility(View.VISIBLE);
                    }
                    else{
                        notibadge1.setVisibility(View.INVISIBLE);
                    }
                    l.clear();
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        l.add(i.getValue(NotificationClass.class));
                    }
                    if (l.size() != 0) {
                        try {
                            notibadge1.setGravity(Gravity.CENTER_VERTICAL);
                            notibadge1.setTypeface(null, Typeface.BOLD);
                            notibadge1.setTextColor(getResources().getColor(R.color.colorAccent));
                            notibadge1.setText(Integer.toString(l.size()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

}
