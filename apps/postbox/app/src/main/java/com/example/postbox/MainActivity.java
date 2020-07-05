package com.example.postbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import com.backendless.persistence.DataQueryBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private DrawerLayout drawer;
    private static final int CODE=123;
    private static final int PICKGALLARY=143;
    private static final int  CAMERA_REQUEST_CODE=526;
    ProgressBar prbar2;
    String nam;
    String id;
    TextView headerusername,headermail;
    private CircleImageView profilepic;
    public static final String LOGINDETAILS="com.example.postbox.data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sha=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        nam=sha.getString("mail","");
        id=sha.getString("id","");
        profilepic=findViewById(R.id.profilepic);
        Toast.makeText(MainActivity.this,"email in get"+id, Toast.LENGTH_SHORT).show();

        Toolbar t=findViewById(R.id.toolbar);
        setSupportActionBar(t);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle a=new ActionBarDrawerToggle(this,drawer,t,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(a);
        NavigationView n=findViewById(R.id.nav_view);
        n.setNavigationItemSelectedListener(this);
        a.syncState();
        View headerview=n.getHeaderView(0);
        prbar2=findViewById(R.id.prbar2);
        prbar2.setVisibility(View.GONE);
        profilepic=headerview.findViewById(R.id.profilepic);
        headerusername=headerview.findViewById(R.id.headerusername);
        headermail=headerview.findViewById(R.id.headermail);
        Backendless.Data.mapTableToClass("People",Admin.class);
        Backendless.Data.of(Admin.class).findById(id, new AsyncCallback<Admin>() {
            @Override
            public void handleResponse(Admin response) {
                headerusername.setText(response.getUsername());
                headermail.setText(response.getEmail());
                if (response.getProfilepic() != null && response.getProfilepic() != ""  )
                    Picasso.with(MainActivity.this).load(response.getProfilepic())
                            .resize(75,75)
                            .centerCrop()
                            .noFade()
                            .error(R.drawable.ic_share)
                            .placeholder(R.drawable.default_profile)
                            .into(profilepic);
                else{
                    Picasso.with(MainActivity.this).load(R.drawable.default_profile).placeholder(R.drawable.default_profile).error(R.drawable.default_profile).noFade()
                            .resize(100,100).centerCrop().into(profilepic);
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this,fault.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new MessageFragment()).commit();
            n.setCheckedItem(R.id.navmessage);
        }

            profilepic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE);
                    } else {
                        Toast.makeText(MainActivity.this, "firstGranted", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
            prbar2.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navmessage:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new MessageFragment()).commit();
                break;
            case R.id.navchat:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new ChatFragment()).commit();
                break;
            case R.id.navchat1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new Chat1Fragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new getProfile()).commit();
                break;
            case R.id.setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new Chat1Fragment()).commit();
                break;
            case R.id.share:Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();break;
            case R.id.send:Toast.makeText(this,"send",Toast.LENGTH_SHORT).show();break;
            case R.id.logout:
               SharedPreferences.Editor e=getSharedPreferences(LOGINDETAILS,MODE_PRIVATE).edit();
               e.putString("mail","");
               e.putString("pass","");
               e.putString("role","");
               e.commit();
               startActivity(new Intent(MainActivity.this,choose_register.class));
               MainActivity.this.finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.about:
                Toast.makeText(this,"about",Toast.LENGTH_LONG).show();break;
            case R.id.cont:
                Toast.makeText(this,"cont",Toast.LENGTH_LONG).show();break;
            case R.id.app_bar_search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }*/
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(CODE==requestCode){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                final  String[] x={"Camera","Chooose File","Cancel"};

                AlertDialog.Builder f=new AlertDialog.Builder(MainActivity.this);
                f.setTitle("Choose from this");
                f.setItems(x, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(x[which]=="Camera"){
                            pickfromcamera();
                        }
                        else if(x[which]=="Chooose File"){
                            pickfromgllery();
                        }
                        else{
                            dialog.dismiss();
                            prbar2.setVisibility(View.GONE);
                        }
                    }
                });
                f.show();
            }
            else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    AlertDialog.Builder dia=new AlertDialog.Builder(this);
                    dia.setMessage("this is important")
                            .setTitle("Required");
                    dia.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CODE);
                        }
                    });
                    dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "waste not Granted", Toast.LENGTH_SHORT).show();
                            prbar2.setVisibility(View.GONE);
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Never showed", Toast.LENGTH_SHORT).show();
                }
            }}
    }
    private String saveToBackendless(Bitmap b){
       Backendless.Files.remove("profilepics/"+nam+"profile", new AsyncCallback<Integer>() {
           @Override
           public void handleResponse(Integer response) {
               Toast.makeText(MainActivity.this,"deleted",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void handleFault(BackendlessFault fault) {
               Toast.makeText(MainActivity.this,"failed to deleted",Toast.LENGTH_SHORT).show();
           }
       });
        Backendless.Files.Android.upload(b, Bitmap.CompressFormat.PNG, 100, nam+"profile", "profilepics", true, new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(BackendlessFile response) {
                Toast.makeText(MainActivity.this,"res"+response.getFileURL(), Toast.LENGTH_LONG).show();
                profilepic.invalidate();
                if (response.getFileURL() != null && response.getFileURL() != "" ) {

                    Picasso.with(MainActivity.this).load(response.getFileURL())
                            .resize(75, 75)
                            .centerCrop()
                            .noFade()
                            .error(R.drawable.ic_share)
                            .placeholder(R.drawable.default_profile)
                            .into(profilepic);
                }
                else{
                    Picasso.with(MainActivity.this).load(R.drawable.default_profile).placeholder(R.drawable.default_profile).error(R.drawable.default_profile).noFade()
                            .resize(100,100).centerCrop().into(profilepic);
                }


                Map<String,Object> m=new HashMap<>();
                m.put("profilepic",response.getFileURL());
                Backendless.Data.of(Admin.class).update("email='" + nam.trim() + "'", m, new AsyncCallback<Integer>() {
                    @Override
                    public void handleResponse(Integer response) {
                       Toast.makeText(MainActivity.this,"done",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this,"not done",Toast.LENGTH_SHORT).show();
                    }
                });
                prbar2.setVisibility(View.GONE);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, "faault"+fault.toString(), Toast.LENGTH_SHORT).show();
                prbar2.setVisibility(View.GONE);
            }

        });
        return null;
    }

    private void pickfromcamera(){
        Intent n=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(n.resolveActivity(getPackageManager())!=null){
            startActivityForResult(n,CAMERA_REQUEST_CODE);
        }

    }
    private void pickfromgllery() {
        Intent n = new Intent(Intent.ACTION_PICK);
        n.setType("image/*");
        String[] mimetypes = {"image/png"};
        n.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        startActivityForResult(n, PICKGALLARY);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            prbar2.setVisibility(View.VISIBLE);
            switch (requestCode) {
                case PICKGALLARY:
                    Bitmap bitmap=null;
                    Uri selectedImage = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    }
                    catch (Exception v){
                        v.printStackTrace();
                    }
                    //profilepic.setImageBitmap(bitmap);
                    saveToBackendless(bitmap);
                    break;
                case CAMERA_REQUEST_CODE:
                    Bundle b=data.getExtras();
                    if(b!=null){
                        Bitmap b1=(Bitmap) b.get("data");
                        //profilepic.setImageBitmap(b1);
                        saveToBackendless(b1);

                    }
                    break;
            }
        }
    }
}
