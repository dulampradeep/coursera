package com.example.postboxoriginal;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotifiesFragment extends Fragment {

    RecyclerView rv;
    NavigationView navigation;
    TextView notibadge1;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    List<NotificationClass> l;
    NotificationsshowAdapter n;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_notify_frag,container,false);
       rv=v.findViewById(R.id.notificationrv);
       l=new ArrayList<>();
       rv.setHasFixedSize(true);

        try {
            navigation =getActivity().findViewById(R.id.nav_view1);
            notibadge1 = (TextView) MenuItemCompat.getActionView(navigation.getMenu().findItem(R.id.notifications));
        }
        catch (Exception e){
            e.printStackTrace();
        }
       notibadge1.setVisibility(View.INVISIBLE);
       rv.setLayoutManager(new LinearLayoutManager(getContext()));
       fd=FirebaseDatabase.getInstance();
       dbr=fd.getReference("notifications").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
       dbr.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               l.clear();
               for(DataSnapshot i:dataSnapshot.getChildren()){
                   l.add(i.getValue(NotificationClass.class));
               }
               n=new NotificationsshowAdapter(l,getContext());
               rv.setAdapter(n);
               n.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

       return v;
    }

}
