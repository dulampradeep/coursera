package com.example.postbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    List<Admin> l;
    Context con;

    public TeacherAdapter( Context con,List<Admin> l) {
        this.l = l;
        this.con = con;
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder{
         TextView tname,temail,phone;
         CircleImageView c;
        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tname=itemView.findViewById(R.id.nameinteachershowstudent);
            temail=itemView.findViewById(R.id.emailinteachershown);
            phone=itemView.findViewById(R.id.phoneinteachershown);
            c=itemView.findViewById(R.id.imageView2inteachershown);
        }
    }
    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherslistinstudents,parent,false);
        return new TeacherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        holder.temail.setText(l.get(position).getEmail());
        holder.tname.setText(l.get(position).getName());
        holder.phone.setText(l.get(position).getPhno());
        if (l.get(position).getProfilepic() != null && l.get(position).getProfilepic() != ""  ) {
            Picasso.with(con).load(l.get(position).getProfilepic())
                    .resize(75, 75)
                    .centerCrop()
                    .noFade()
                    .error(R.drawable.ic_share)
                    .placeholder(R.drawable.default_profile)
                    .into(holder.c);
        }
        else{
            Picasso.with(con).load(R.drawable.default_profile).placeholder(R.drawable.default_profile).error(R.drawable.default_profile).noFade()
                    .resize(100,100).centerCrop().into(holder.c);
        }
    }

    @Override
    public int getItemCount() {
        if(l==null)
            return 0;
        else
            return l.size();
    }
}
