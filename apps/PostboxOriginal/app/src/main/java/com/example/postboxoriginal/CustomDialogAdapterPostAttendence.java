package com.example.postboxoriginal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogAdapterPostAttendence extends ArrayAdapter<Admin> {
    private final Context context;
    private final ArrayList<Admin> p;
    TextView tv1,tv2;

    public CustomDialogAdapterPostAttendence(@NonNull Context context, ArrayList<Admin> p) {
        super(context,R.layout.listviewonpostattendence,p);
        this.context=context;
        this.p=p;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inf.inflate(R.layout.listviewonpostattendence,parent,false);
        tv1=v.findViewById(R.id.usernamecustomlist);
        tv2=v.findViewById(R.id.passwordincustomlist);

        tv1.setText(p.get(position).getUsername());
        tv2.setText(p.get(position).getName());
        return v;
    }
}
