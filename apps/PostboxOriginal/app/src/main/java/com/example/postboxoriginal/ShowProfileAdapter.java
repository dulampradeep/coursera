package com.example.postboxoriginal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postboxoriginal.R;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ShowProfileAdapter extends RecyclerView.Adapter<ShowProfileAdapter.ProfileViewHolder> {
    Context c;
    String headings[];
    Integer icons[];
    String attr[];

    public ShowProfileAdapter(Context c, String[] attr) {
        this.c = c;
        this.attr = attr;
        headings=new String[]{"Username","Email","Name","Password","Phno","Role","Year","Section","College","Branch"};
        icons=new Integer[]{R.drawable.username,R.drawable.email,R.drawable.profile,R.drawable.password,R.drawable.phone,R.drawable.role,R.drawable.year,R.drawable.section,R.drawable.college,R.drawable.branch};

    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.profilecustomstyle,parent,false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {
        if(position==1||position==5||position==6||position==7||position==8||position==9){
            holder.edit.setVisibility(View.GONE);
        }
        holder.iv.setImageResource(icons[position]);
        holder.thead.setText(headings[position]);
        holder.tattr.setText(attr[position]);
        final int[] t = new int[10];
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t[position]==0) {
                    holder.ed.setText(holder.tattr.getText().toString());
                    holder.edit.setImageResource(R.drawable.submit);
                    holder.tattr.setVisibility(View.GONE);
                    holder.ed.setVisibility(View.VISIBLE);
                    t[position] =1;
                }
                else{
                   if(holder.ed.getText().toString()!=""){
                       FirebaseDatabase fd=FirebaseDatabase.getInstance();
                       DatabaseReference db=fd.getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/").child(headings[position].toLowerCase());

                       t[position] =0;
                       db.setValue(holder.ed.getText().toString())
                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       holder.edit.setImageResource(R.drawable.edit);
                                       holder.tattr.setVisibility(View.VISIBLE);
                                       holder.ed.setVisibility(View.GONE);
                                   }
                               });
                    }
                   else{
                       Toast.makeText(c, "need non empty", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return headings.length;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView thead,tattr;
        ImageView iv,edit;
        EditText ed;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.tvicon);
            thead=itemView.findViewById(R.id.tvhead);
            tattr=itemView.findViewById(R.id.tvattribute);
            edit=itemView.findViewById(R.id.editbut);
            ed=itemView.findViewById(R.id.edittext111);
           }
    }
}
