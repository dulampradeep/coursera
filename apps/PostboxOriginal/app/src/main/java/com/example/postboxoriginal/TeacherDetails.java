package com.example.postboxoriginal;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.postboxoriginal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeacherDetails extends Fragment {
    RecyclerView rv,rv2;
    RecyclerView.Adapter rvadp,rvadp2;
    RecyclerView.LayoutManager rvlm,rvlm2;
    String curruser;
    List<Admin> l1,l2;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    Query messagescount;
    ProgressBar pv,pv2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_teacher_details2,container,false);
        fd=FirebaseDatabase.getInstance();
        curruser= FirebaseAuth.getInstance().getUid();
        dbr=fd.getReference().child("users");
        messagescount=dbr.child(curruser).child("messagescount").orderByChild("count");
        final TabHost tabhost=v.findViewById(R.id.tabhost);
        tabhost.setup();
        l1=new ArrayList<Admin>();
        l2=new ArrayList<Admin>();
        TabHost.TabSpec spec=tabhost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Recent");
        tabhost.addTab(spec);
        spec=tabhost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("All");
        tabhost.addTab(spec);
        rv=(RecyclerView)v.findViewById(R.id.recyclerviewteacher);
        rv2=(RecyclerView)v.findViewById(R.id.recyclerviewstudent);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm =new LinearLayoutManager(getContext());
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        rvlm=llm;
        rv.setLayoutManager(rvlm);
        rv2.setHasFixedSize(true);
        rvlm2=new LinearLayoutManager(getContext());

        rv2.setLayoutManager(rvlm2);
        pv=v.findViewById(R.id.recentprogress);
        pv2=v.findViewById(R.id.recentprogress2);
        messagescount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot1) {
                l1.clear();
                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot i:dataSnapshot1.getChildren()) {
                            String s=i.getKey();
                            for(DataSnapshot j:dataSnapshot.getChildren()){
                                try {
                                    if (j.getValue(Admin.class).getUserId().equals(s)) {
                                        l1.add(j.getValue(Admin.class));
                                    }
                                }
                                catch (Exception d){
                                    d.printStackTrace();
                                }
                            }

                            rvadp2=new TeacherAdapter(getContext(),l1);
                            rv.setAdapter(rvadp2);

                            pv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                l2.clear();
                for(DataSnapshot keys:dataSnapshot.getChildren()){
                    Admin v=keys.getValue(Admin.class);
                    l2.add(v);
                }

                rvadp=new TeacherAdapter(getContext(),l2);
                rv2.setAdapter(rvadp);
                pv2.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"database error",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}

