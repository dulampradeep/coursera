package com.example.postbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class getProfile extends Fragment{
    private CircleImageView c;
    String id;
    private static final String LOGINDETAILS="com.example.postbox.data";
    private TextView uname,email,name,pass,phone,sec,college,year,role,branch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_get_profile,container,false);
        SharedPreferences shar=getActivity().getSharedPreferences(LOGINDETAILS,MODE_PRIVATE);
        id=shar.getString("id","");
        c=v.findViewById(R.id.profilelarge);
        uname=v.findViewById(R.id.uname);
        email=v.findViewById(R.id.uemail);
        name=v.findViewById(R.id.username1);
        pass=v.findViewById(R.id.upass);
        phone=v.findViewById(R.id.uphone);
        sec=v.findViewById(R.id.usec);
        college=v.findViewById(R.id.ucoll);
        year=v.findViewById(R.id.uyear);
        role=v.findViewById(R.id.urole);
        branch=v.findViewById(R.id.ubranch);
        Backendless.Data.of(Admin.class).findById(id, new AsyncCallback<Admin>() {
            @Override
            public void handleResponse(Admin response) {
                c.invalidate();
                if (response.getProfilepic()!= null && response.getProfilepic() != "") {
                    Picasso.with(getContext()).load(response.getProfilepic()).placeholder(R.drawable.default_profile).error(R.drawable.ic_share).noFade()
                            .resize(100, 100).centerCrop().into(c);
                }
                else{
                    Picasso.with(getContext()).load(R.drawable.default_profile).placeholder(R.drawable.default_profile).error(R.drawable.default_profile).noFade()
                            .resize(100,100).centerCrop().into(c);
                }
                uname.setText(response.getUsername());
                email.setText(response.getEmail());
                name.setText(response.getName());
                pass.setText(response.getPassword());
                phone.setText(response.getPhno());
                sec.setText(response.getSec());
                college.setText(response.getCollege());
                year.setText(response.getYear());
                role.setText(response.getRole());
                branch.setText(response.getBranch());
                Toast.makeText(getContext(),"completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getContext(),"failed to push",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}