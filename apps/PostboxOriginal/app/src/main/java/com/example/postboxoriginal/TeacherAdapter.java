package com.example.postboxoriginal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postboxoriginal.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.spec.ECField;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>  {
    List<Admin> l;
    Context con;
    String curruser;
    FirebaseDatabase fd;
    DatabaseReference dbr;

    public TeacherAdapter(Context con, List<Admin> l) {
        this.l = l;
        this.con = con;
        curruser = FirebaseAuth.getInstance().getUid();
        fd = FirebaseDatabase.getInstance();
        dbr = fd.getReference().child("users").child(curruser).child("messagescount");
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView tname, temail, phone, notification;
        CircleImageView c;
        MaterialCardView m;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.nameinteachershowstudent);
            temail = itemView.findViewById(R.id.emailinteachershown);
            phone = itemView.findViewById(R.id.phoneinteachershown);
            c = itemView.findViewById(R.id.imageView2inteachershown);
            m = itemView.findViewById(R.id.teacherlist);
            notification = itemView.findViewById(R.id.notificationcount);
        }
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherslistinstudents, parent, false);

        return new TeacherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeacherViewHolder holder, final int position) {

        holder.temail.setText(l.get(position).getEmail());
        holder.tname.setText(l.get(position).getName());
        holder.phone.setText(l.get(position).getPhno());
        Picasso.get().load(Uri.parse(l.get(position).getProfilepic()))
                .error(R.drawable.default_profile)
                .placeholder(R.drawable.default_profile)
                .into(holder.c);

        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bui = new AlertDialog.Builder(con);
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dv = LayoutInflater.from(con).inflate(R.layout.customdialogprofile, viewGroup, false);
                ImageView ivsh = dv.findViewById(R.id.imageView2);
                Picasso.get().load(Uri.parse(l.get(position).getProfilepic()))
                        .error(R.drawable.default_profile)
                        .placeholder(R.drawable.default_profile)
                        .into(ivsh);

                bui.setView(dv);
                AlertDialog alertDialog = bui.create();
                alertDialog.show();
            }

        });
        DatabaseReference dbr1 = dbr.child(l.get(position).getUserId()).child("count");

        dbr1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    int c = dataSnapshot.getValue(int.class);
                    if (c == 0) {
                        holder.notification.setVisibility(View.GONE);
                    } else {
                        holder.notification.setText(String.valueOf(c));
                        holder.notification.setVisibility(View.VISIBLE);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n1 = new Intent(con, chattingactivity.class);
                Bundle b1 = new Bundle();
                b1.putString("name", l.get(position).getName());
                b1.putString("profilepic", l.get(position).getProfilepic());
                b1.putString("receiver", l.get(position).getUserId());
                n1.putExtra("bundle", b1);
                con.startActivity(n1);
                dbr.child(l.get(position).getUserId()).child("count").setValue(0);

            }
        });
    }

    @Override
    public int getItemCount() {

        if (l == null)
            return 0;
        else {
            return l.size();
        }
    }
/*
    class comparetwo implements Comparator<Admin> {
        @Override
        public int compare(Admin o1, Admin o2) {
            String n = FirebaseAuth.getInstance().getUid();
            int a = 0, b = 0;
            try {
                a = o1.getMessagescount().get(n).getCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                b = o2.getMessagescount().get(n).getCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (a < b)
                return -1;
            else if (a > b)
                return 1;
            else {
                return 0;
            }
        }
    }*/


}
