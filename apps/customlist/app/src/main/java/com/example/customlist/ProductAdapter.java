package com.example.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final ArrayList<Product> p;
    ImageView iv1,iv2;
    TextView tv1,tv2,tv3;

    public ProductAdapter(Context context, ArrayList<Product> p) {
        super(context,R.layout.listdesign,p);
        this.context = context;
        this.p = p;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inf.inflate(R.layout.listdesign,parent,false);
        iv1=v.findViewById(R.id.ivlist1);
        iv2=v.findViewById(R.id.ivlist2);
        tv1=v.findViewById(R.id.tvlist1);
        tv2=v.findViewById(R.id.tvlist2);
        tv3=v.findViewById(R.id.tvlist3);

        iv1.setImageResource(ProductApplication.x[ProductApplication.p.get(position).getType()]);
        if(ProductApplication.p.get(position).getSale()){
            iv2.setImageResource(R.drawable.best_price);
        }
        else
            iv2.setImageResource(R.drawable.on_sale_big);
        tv1.setText(ProductApplication.p.get(position).getName());
        tv2.setText(ProductApplication.p.get(position).getModel());
        tv3.setText(ProductApplication.p.get(position).getDesc());
        return v;
    }
}
