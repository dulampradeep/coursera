package com.example.books;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllBooksRecyclerViewAdapter extends RecyclerView.Adapter<AllBooksRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Book> data=new ArrayList<Book>();
    private Context con;

    public AllBooksRecyclerViewAdapter(Context con) {
        this.con = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("hai","entered on bind View Holder");
        holder.itemView.setTag(data.get(position));
        holder.tv1.setText(data.get(position).getName());
        Glide.with(con)
                .asBitmap()
                .load(data.get(position).getImageUrl())
                .into(holder.img1);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con,data.get(position).getName()+"clicked",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setBooks(ArrayList<Book> a){
        data=a;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView img1;
        private TextView tv1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.cardview1);
            img1=itemView.findViewById(R.id.logo);
            tv1=itemView.findViewById(R.id.name);
        }
    }
}
