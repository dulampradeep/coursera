package com.example.postboxoriginal;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadTimetable extends Fragment {
    private Spinner subname,hour,branch,section,college,year,day;
    private List yearlist;
    private List seclist;
    private List subnamelist;
    private List hourlist;
    private List colllist;
    private List daylist;
    private RecyclerView timetablerecyclerview;
    RecyclerView.Adapter rvad;
    RecyclerView.LayoutManager lm;
    ArrayList<Timetable> listtimetables;
    Button addperiod;
    private FirebaseDatabase fd;
    private DatabaseReference dr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_uploadtimetable,container,false);
        fd=FirebaseDatabase.getInstance();
       dr=fd.getReference("timetables").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        subname=v.findViewById(R.id.subjectspinner1);
        hour=v.findViewById(R.id.hourspinner1);
        branch=v.findViewById(R.id.branchspinner1);
        section=v.findViewById(R.id.sectionspinner1);
        college=v.findViewById(R.id.collegespinner1);
        year=v.findViewById(R.id.yearspinner1);
        day=v.findViewById(R.id.dayspinner1);
        addperiod=v.findViewById(R.id.addtimetametableperiod);
        listtimetables=new ArrayList<Timetable>();
        timetablerecyclerview=v.findViewById(R.id.timetablerecyclerview);
        List branchlist = new ArrayList<String>();
       yearlist=new ArrayList<String>();
       seclist=new ArrayList<String>();
       colllist=new ArrayList<String>();
       hourlist=new ArrayList<String>();
       daylist=new ArrayList<String>();
       subnamelist=new ArrayList<String>();
        branchlist.add("CSE");
        branchlist.add("ECE");
        branchlist.add("EEE");
        branchlist.add("IT");
        branchlist.add("CIVIL");
        seclist.add("A");
        seclist.add("B");
        seclist.add("C");
        yearlist.add("First");
        yearlist.add("Second");
        yearlist.add("Third");
        yearlist.add("Fourth");
        colllist.add(new String("Scet"));
        colllist.add(new String("Siet"));
        colllist.add(new String("swrn"));
        daylist.add("Monday");
        daylist.add("Tuesday");
        daylist.add("Wednesday");
        daylist.add("Thursday");
        daylist.add("Friday");
        daylist.add("Saturday");

        for(int i=1;i<=8;i++){
            hourlist.add(Integer.toString(i));
        }
        subnamelist.add("Daa");
        subnamelist.add("C");
        subnamelist.add("Cpp");
        subnamelist.add("java");
        subnamelist.add("python");
        subnamelist.add("ds");
        subnamelist.add("Advan java");
        subnamelist.add("cloud");

        ArrayAdapter secadpday=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,daylist);
        secadpday.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        day.setAdapter(secadpday);

        ArrayAdapter secadp=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,subnamelist);
        secadp.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        subname.setAdapter(secadp);

        ArrayAdapter secadp1=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,hourlist);
        secadp1.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        hour.setAdapter(secadp1);

        ArrayAdapter secadp2=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected, branchlist);
        secadp2.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        branch.setAdapter(secadp2);

        ArrayAdapter secadp3=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,seclist);
        secadp3.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        section.setAdapter(secadp3);

        ArrayAdapter secadp4=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,colllist);
        secadp4.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        college.setAdapter(secadp4);

        ArrayAdapter secadp5=new ArrayAdapter<String>(getContext(),R.layout.spinner_item_selected,yearlist);
        secadp5.setDropDownViewResource(R.layout.spinnerdropdowncolor);
        year.setAdapter(secadp5);
        timetablerecyclerview.setHasFixedSize(true);
        lm=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
        timetablerecyclerview.setLayoutManager(lm);
        addperiod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timetable  t=new Timetable();
                t.setDay(day.getSelectedItem().toString());
                t.setBranch(branch.getSelectedItem().toString());
                t.setHour(hour.getSelectedItem().toString());
                t.setCollege(college.getSelectedItem().toString());
                t.setYear(year.getSelectedItem().toString());
                t.setSec(section.getSelectedItem().toString());
                t.setSubname(subname.getSelectedItem().toString());
                DatabaseReference dbr=dr.child(t.getDay()+"/"+t.getHour());
                dbr.setValue(t, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(getContext(),"Uploded",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listtimetables.clear();
                for(DataSnapshot i:dataSnapshot.getChildren()){
                    for(DataSnapshot j:i.getChildren()){
                       Timetable t=j.getValue(Timetable.class);
                        listtimetables.add(t);
                    }
                }
                rvad=new UploadTimeTableAdapter(getContext(),listtimetables);
                timetablerecyclerview.setAdapter(rvad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"pushed error",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
