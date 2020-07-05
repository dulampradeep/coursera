package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> p;
    PassItem p1;
    public interface PassItem{
        void getItemPa(int index);
    }
    public PersonAdapter(Context  context,ArrayList<Person> p){
        this.p=p;
        p1=(PassItem)context;
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(p.get(position));
        holder.tv1.setText(p.get(position).getName());
        holder.tv2.setText(p.get(position).getSurname());
        if(p.get(position).getImg()==1){
            holder.iv.setImageResource(R.drawable.ic_launcher_background);
        }
        else{
            holder.iv.setImageResource(R.drawable.ic_launcher_foreground);
        }

    }

    @Override
    public int getItemCount() {
        return p.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1,tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imageView2);
            tv1=itemView.findViewById(R.id.textView);
            tv2=itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1.getItemPa(p.indexOf(v.getTag()));
                }
            });
        }
    }
}
