package com.example.postboxoriginal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UploadTimeTableAdapter extends RecyclerView.Adapter<UploadTimeTableAdapter.TimeAdapter> {
    List<Timetable> l;
    Context con;

    public UploadTimeTableAdapter( Context con,List<Timetable> l) {
        this.l = l;
        this.con = con;
    }

    public static class TimeAdapter extends RecyclerView.ViewHolder{
        TextView subname,branch,sec,year,hour,college,day;
        public TimeAdapter(@NonNull View itemView) {
            super(itemView);
            subname=itemView.findViewById(R.id.subname);
            branch=itemView.findViewById(R.id.branchname);
            sec=itemView.findViewById(R.id.sectionname);
            year=itemView.findViewById(R.id.yearname);
            hour=itemView.findViewById(R.id.periodnumber);
            college=itemView.findViewById(R.id.collegename);
            day=itemView.findViewById(R.id.daynumber);
        }
    }
    @NonNull
    @Override
    public TimeAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.timetableshowdesignlist,parent,false);
        return new TimeAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter holder, int position) {
            holder.hour.setText(l.get(position).getHour());
            holder.sec.setText(l.get(position).getSec());
            holder.college.setText(l.get(position).getCollege());
            holder.year.setText(l.get(position).getYear());
            holder.subname.setText(l.get(position).getSubname());
            holder.branch.setText(l.get(position).getBranch());
            holder.day.setText(l.get(position).getDay());

    }

    @Override
    public int getItemCount() {
        if(l==null)
            return 0;
        else
            return l.size();
    }

}
