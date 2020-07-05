package com.example.postboxoriginal;

import android.content.Context;
import android.net.Uri;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesShowAdapter extends RecyclerView.Adapter<MessagesShowAdapter.MessagesHolder> {
    Context c;
    private static final int  SENDED = 0;
    private static final int RECEIVED = 1;
    String profile;
    ArrayList<Messagesclass> l;
    int flag;
    String curruser;
    FirebaseDatabase fd;
    DatabaseReference dbr,dbr1;
    public MessagesShowAdapter(Context c, ArrayList<Messagesclass> l,String profile) {
        this.c = c;
        this.l = l;
        this.profile=profile;
        flag=0;
        fd=FirebaseDatabase.getInstance();
        dbr=fd.getReference("messages");
        dbr1=fd.getReference("users");
        curruser=FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    class MessagesHolder extends RecyclerView.ViewHolder{
        private TextView msg,time;
        CircleImageView cir;
        ImageView status;
        LinearLayout fr;
        public MessagesHolder(@NonNull View itemView) {
            super(itemView);
                fr=itemView.findViewById(R.id.se);
                msg=itemView.findViewById(R.id.sendermessage);
                time=itemView.findViewById(R.id.timeofmessage);
                status=itemView.findViewById(R.id.status);
                cir=itemView.findViewById(R.id.profileinmessage);

        }
    }

    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
        if(viewType==SENDED){
            v= LayoutInflater.from(c).inflate(R.layout.sendermessagelayout,parent,false);
            return new MessagesHolder(v);
        }
        else{
            v= LayoutInflater.from(c).inflate(R.layout.receivermessagelayout,parent,false);
            return new MessagesHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MessagesHolder holder, final int position) {
            if(curruser.equals(l.get(position).getSender())){
             holder.fr.setGravity(Gravity.END );
            }

            //SimpleDateFormat d=new SimpleDateFormat("hh:mm: a");
            holder.msg.setText(l.get(position).getMessage());
            //holder.time.setText(d.format(l.get(position).getTimestamp()));
            if(l.get(position).getStatus().equals("seen")){
                holder.status.setImageResource(R.drawable.seen);
            }
            else{
                holder.status.setImageResource(R.drawable.sent);
            }
        if(curruser.equals(l.get(position).getReceiver())) {
            DatabaseReference db = dbr.child(l.get(position).getMessageid()).child("status");
            db.setValue("seen");
            holder.msg.setText(l.get(position).getMessage());
            Picasso.get().load(Uri.parse(profile))
                    .error(R.drawable.default_profile)
                    .placeholder(R.drawable.default_profile)
                    .into(holder.cir);
        }

        final Long val=Long.parseLong(l.get(position).getTimestamp());
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

    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(curruser.equals(l.get(position).getSender())){
            return SENDED;
        }
        else {
            return RECEIVED;
        }
    }
}
