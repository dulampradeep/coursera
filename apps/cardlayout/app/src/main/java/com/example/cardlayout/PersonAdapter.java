package com.example.cardlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    private ArrayList<Person> people;
    ClickedItem ci;
    public interface ClickedItem{
        void getClicked(int index);
    }

    public PersonAdapter(Context context,ArrayList<Person> list){
        people=list;
        ci=(ClickedItem)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivpref;
        TextView tvname,tvsurname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivpref=itemView.findViewById(R.id.ivpref);
            tvname=itemView.findViewById(R.id.tvname);
            tvsurname=itemView.findViewById(R.id.tvsurname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ci.getClicked(people.indexOf((Person)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(people.get(position));
        holder.tvname.setText(people.get(position).getName());
        holder.tvsurname.setText(people.get(position).getSurname());
        if(people.get(position).getPreference().equals("bus"))
        holder.ivpref.setImageResource(R.drawable.bus);
        else
            holder.ivpref.setImageResource(R.drawable.flight);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
