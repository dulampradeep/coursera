package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
interface SendIndex{
    void senddata(int index);
}
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> p;
    SendIndex se;
    public PersonAdapter(Context c,ArrayList<Person> p) {
        this.p=p;
        se=(SendIndex)c;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView l1name;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            l1name=itemView.findViewById(R.id.haitv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    se.senddata(p.indexOf((Person) v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override

    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listdesign,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(p.get(position));
        holder.l1name.setText(p.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return p.size();
    }
}
