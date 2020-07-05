package com.example.postbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Permission;
import java.security.PrivateKey;

public class ProfilePic extends AppCompatActivity {
    Button b;
    private static final int CODE=123;
    ImageView imv;
    private static final int PICKGALLARY=143;
    private static final int  CAMERA_REQUEST_CODE=526;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        imv=findViewById(R.id.imv);
        b=findViewById(R.id.permission);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((ActivityCompat.checkSelfPermission(ProfilePic.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) ||(ActivityCompat.checkSelfPermission(ProfilePic.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(ProfilePic.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CODE);
                }
                else{
                    Toast.makeText(ProfilePic.this, "firstGranted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(CODE==requestCode){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
              final  String[] x={"Camera","Chooose File","Cancel"};

                AlertDialog.Builder f=new AlertDialog.Builder(ProfilePic.this);
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
                        }
                    }
                });
                f.show();
            }
            else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(ProfilePic.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                AlertDialog.Builder dia=new AlertDialog.Builder(this);
                dia.setMessage("this is important")
                        .setTitle("Required");
                dia.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ProfilePic.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CODE);
                    }
                });
                dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProfilePic.this, "waste not Granted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(ProfilePic.this, "Never showed", Toast.LENGTH_SHORT).show();
            }
        }}
    }
    private String saveToBackendless(Bitmap b){
       Backendless.Files.Android.upload(b, Bitmap.CompressFormat.PNG, 100, "profile", "profilepics", true, new AsyncCallback<BackendlessFile>() {
           @Override
           public void handleResponse(BackendlessFile response) {
               Toast.makeText(ProfilePic.this,"res"+response.getFileURL(), Toast.LENGTH_LONG).show();
               Glide.with(ProfilePic.this).load(response.getFileURL()).into(imv);
           }

           @Override
           public void handleFault(BackendlessFault fault) {
               Toast.makeText(ProfilePic.this, "faault"+fault.toString(), Toast.LENGTH_SHORT).show();
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
        String[] mimetypes = {"image/jpeg", "image/png"};
        n.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        startActivityForResult(n, PICKGALLARY);

    }
    /*
    private byte[] bitmaptobyte(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte [] byteArray = byteBuffer.array();
        return byteArray;
    }
    private Bitmap byteToBitmap(byte[] bitmapdata){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        return bitmap;
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICKGALLARY:
                    Bitmap bitmap=null;
                    Uri selectedImage = data.getData();
                    try {
                        bitmap =
                                MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    }
                    catch (Exception v){
                        v.printStackTrace();
                    }
                    saveToBackendless(bitmap);
                    break;
                case CAMERA_REQUEST_CODE:
                    Bundle b=data.getExtras();
                    if(b!=null){
                        Bitmap b1=(Bitmap) b.get("data");
                        saveToBackendless(b1);
                        break;
            }
        }
    }
}}
