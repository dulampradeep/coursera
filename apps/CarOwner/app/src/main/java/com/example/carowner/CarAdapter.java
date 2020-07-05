package com.example.carowner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    public interface SendData{
        void sendIndex(int i);
    }
    SendData s;
    ArrayList<CarInform> cr;
    public CarAdapter(Context context, ArrayList<CarInform> c){
        cr=c;
        s=(SendData)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv1,tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ivlistdesign);
            tv1=itemView.findViewById(R.id.tvlistdesign);
            tv2=itemView.findViewById(R.id.tv2listdesign);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.sendIndex(cr.indexOf(v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listdesign,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        int a[]={R.drawable.mercedes,R.drawable.nissan,R.drawable.volkswagen};
        holder.itemView.setTag(cr.get(position));
        holder.tv1.setText(cr.get(position).getCarnumber());
        holder.tv2.setText(cr.get(position).getName());
        holder.img.setImageResource(a[cr.get(position).getI()]);

    }

    @Override
    public int getItemCount() {
        return cr.size();
    }
}
