package com.example.postboxoriginal;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsshowAdapter extends RecyclerView.Adapter<NotificationsshowAdapter.NotificationHolder> {
    private List<NotificationClass> l;
    private Context c;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    int count[];
    public NotificationsshowAdapter(List<NotificationClass> l, Context c) {
        this.l = l;
        this.c = c;
        count=new int[l.size()];
    }

    public class NotificationHolder extends RecyclerView.ViewHolder{
        CircleImageView profile;
        TextView period ,sub,status,time,replymsg;

        ImageButton b,b1,send;
        LinearLayout layout1;
        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            layout1=itemView.findViewById(R.id.replymessagelayout);
            profile=itemView.findViewById(R.id.notificationprofile);
            period=itemView.findViewById(R.id.notificationperiod);
            sub=itemView.findViewById(R.id.notificationsub);
            status=itemView.findViewById(R.id.notificationstatus);
            time=itemView.findViewById(R.id.notificationtime);
            b=itemView.findViewById(R.id.deletenotification);
            replymsg=itemView.findViewById(R.id.replytextview);
            send=itemView.findViewById(R.id.sendreply);
            b1=itemView.findViewById(R.id.reply);
        }
    }
    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationstructure,parent,false);
        return new NotificationHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationHolder holder, final int position) {
        fd=FirebaseDatabase.getInstance();
        final String[] profileurl = new String[1];
        dbr=fd.getReference("users").child(l.get(position).getTeacherid()).child("profilepic");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileurl[0] =dataSnapshot.getValue(String.class);
                Picasso.get().load(Uri.parse(profileurl[0]))
                        .error(R.drawable.default_profile)
                        .placeholder(R.drawable.default_profile)
                        .into(holder.profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(l.get(position).getStatus()){
            holder.b1.setVisibility(View.GONE);
        }
        holder.period.setText("Period : "+l.get(position).getTimetable().getHour());
        if(l.get(position).getStatus()) {
            holder.status.setText("Your Present !!");
            holder.status.setTextColor(Color.GREEN);
        }
        else{
            holder.status.setText("Your Absent !!");
            holder.status.setTextColor(Color.RED);
        }
        holder.sub.setText(l.get(position).getTimetable().getSubname());

        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db=fd.getReference("notifications/"+l.get(position).getUserid()+"/"+l.get(position).getTimetable().getHour()+"/");
                db.setValue(null);
            }
        });
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count[position]==0) {
                    holder.layout1.setVisibility(View.VISIBLE);
                    holder.b1.setImageResource(R.drawable.close);
                    count[position]=1;
                }
                else{
                    holder.layout1.setVisibility(View.GONE);
                    holder.b1.setImageResource(R.drawable.replynotif);
                    count[position]=0;
                }
            }
        });
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.replymsg.getText().toString()!=null){
                    DatabaseReference dbrsend=fd.getReference("messages");
                    Messagesclass m=new Messagesclass();
                    m.setMessage(holder.replymsg.getText().toString());
                    m.setSender(l.get(position).getUserid());
                    m.setReceiver(l.get(position).getTeacherid());
                    m.setStatus("unseen");
                    m.setTimestamp(String.valueOf(System.currentTimeMillis()));
                    String id=dbrsend.push().getKey();
                    m.setMessageid(id);
                    dbrsend.child(id).setValue(m);
                    holder.replymsg.setText("");
                }
            }
        });
        final Long val=Long.parseLong(l.get(position).getTimemillis());
        long seconds=(System.currentTimeMillis() - val) / 1000;
        if (seconds < 60) {
            holder.time.setText("Just Now");
        } else if(seconds/60 <60){
            holder.time.setText(seconds/60+ "min ago");
        }else if((seconds/60)/60 <24){
            holder.time.setText((seconds/60)/60+ "hr ago");
        }
        else if((seconds/60)/60/24<31){
            holder.time.setText((seconds/60)/60/24+ "days ago");
        }else if((seconds/60)/60/24/31<12) {
            holder.time.setText((seconds / 60) / 60 / 24/31 + "months ago");
        }
           else {
            holder.time.setText(((seconds/60)/60)/24/31/12+ "years ago");
        }
        /*
        final Long val=Long.parseLong(l.get(position).getTimemillis());
           new Thread() {
               @Override
               public void run() {

                  try {
                      while(true) {
                          settime(holder, (System.currentTimeMillis() - val) / 1000);
                          sleep(2000);
                      }
                  }
                  catch (Exception e){
                      e.printStackTrace();
                  }
               }
           }.start();
*/
    }/*
void settime(NotificationHolder holder,Long seconds){
        if (seconds < 60) {
            holder.time.setText("Just Now");
        } else if(seconds/60 >=1){
            holder.time.setText(seconds/60+ "min ago");
        }else if((seconds/60)/60 >=1){
            holder.time.setText((seconds/60)/60+ "hr ago");
        }
        else{
            holder.time.setText(((seconds/60)/60)/24+ "days ago");
        }
}*/
    @Override
    public int getItemCount() {
        return l.size();
    }
}
