package com.example.postboxoriginal;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.postboxoriginal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class getProfile extends Fragment {
    private FirebaseAuth mauth;

    private StorageReference mstorageref;
    private CircleImageView c;
    String url;
    private static final int CODE = 123;
    String[] attr;
    RecyclerView rv1;
    RecyclerView.Adapter adp;
//    private static final String LOGINDETAILS = "com.example.postbox.data";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_get_profile, container, false);
        c = v.findViewById(R.id.profilelarge);
        final ImageView ivsh[] = new ImageView[15];
        rv1 = v.findViewById(R.id.profilerv);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getContext()));

        mauth = FirebaseAuth.getInstance();
        mstorageref = FirebaseStorage.getInstance().getReference();
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference dbr = fd.getReference("users/").child(mauth.getCurrentUser().getUid());
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Admin data = dataSnapshot.getValue(Admin.class);
                url = data.getProfilepic();
                Picasso.get().load(Uri.parse(data.getProfilepic()))
                        .error(R.drawable.default_profile)
                        .placeholder(R.drawable.default_profile)
                        .into(c);
                attr = new String[]{data.getUsername(), data.getEmail(), data.getName(), data.getPassword(), data.getPhno(), data.getRole(), data.getYear(), data.getSec(), data.getCollege(), data.getBranch()};
                adp = new ShowProfileAdapter(getContext(), attr);
                rv1.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bui = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dv = LayoutInflater.from(getContext()).inflate(R.layout.customdialogprofile, viewGroup, false);
                ivsh[0] = dv.findViewById(R.id.imageView2);
                Picasso.get().load(Uri.parse(url))
                        .error(R.drawable.default_profile)
                        .placeholder(R.drawable.default_profile)
                        .into(ivsh[0]);

                bui.setView(dv);
                AlertDialog alertDialog = bui.create();
                alertDialog.show();


            }
        });
        return v;
    }

}