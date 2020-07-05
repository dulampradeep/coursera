package com.example.postboxoriginal;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MultichooseAdapter extends MultiChoiceAdapter<MultichooseAdapter.MyViewHolder> {
    List<Admin> l;
    Context con;

    public MultichooseAdapter( Context con,List<Admin> l) {
        this.l = l;
        this.con = con;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tname,temail,phone;
        CircleImageView c;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tname=itemView.findViewById(R.id.nameinteachershowstudent);
            temail=itemView.findViewById(R.id.emailinteachershown);
            phone=itemView.findViewById(R.id.phoneinteachershown);
            c=itemView.findViewById(R.id.imageView2inteachershown);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentslistinattendence,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        if(l==null)
            return 0;
        else
            return l.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.temail.setText(l.get(position).getName());
        holder.tname.setText(l.get(position).getUsername());
        holder.phone.setText(l.get(position).getPhno());
        Picasso.get().load(Uri.parse(l.get(position).getProfilepic()))
                .error(R.drawable.default_profile)
                .placeholder(R.drawable.default_profile)
                .into(holder.c);

        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder bui=new AlertDialog.Builder(con);
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dv=LayoutInflater.from(con).inflate(R.layout.customdialogprofile,viewGroup,false);
                ImageView ivsh =dv.findViewById(R.id.imageView2);
                Picasso.get().load(Uri.parse(l.get(position).getProfilepic()))
                        .error(R.drawable.default_profile)
                        .placeholder(R.drawable.default_profile)
                        .into(ivsh);

                bui.setView(dv);
                AlertDialog alertDialog=bui.create();
                alertDialog.show();
            }

        });

    }

}
