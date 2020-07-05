package com.example.postboxoriginal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourthPeriodFrag extends Fragment {
    RecyclerView rv;
    MultichooseAdapter m1;
    RecyclerView.LayoutManager rvlm;
    List<Admin> l;
    Date d;
    Timetable t1;
    FirebaseDatabase fd;
    DatabaseReference dbr,userdbr;
    SimpleDateFormat day,year,month,week;
    String date;
    ImageView noperiod;
    FloatingActionButton fab;
    public FourthPeriodFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fourthperiodfrag,container,false);
        rv=v.findViewById(R.id.rvfourthfrag);
        fab=v.findViewById(R.id.fourthfloatingbutton);
        noperiod=v.findViewById(R.id.noperiod4);
        noperiod.setVisibility(View.INVISIBLE);
        d=new Date();
        l=new ArrayList<Admin>();
        day=new SimpleDateFormat("EEEE");
        year=new SimpleDateFormat("yyyy");
        month=new SimpleDateFormat("MMMM");
        week=new SimpleDateFormat("W");
        date=day.format(d);
        fd= FirebaseDatabase.getInstance();
        dbr=fd.getReference().child("timetables/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+date+"/4/");
        userdbr=fd.getReference().child("users");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                t1=dataSnapshot.getValue(Timetable.class);
                if(t1==null){
                    noperiod.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                }
                else{
                    fab.setVisibility(View.VISIBLE);
                    iterateusers(t1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  v;
    }

    private void iterateusers(final Timetable t){
        final Set<Admin> attededlist = new HashSet<Admin>();
        userdbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                l.clear();
                for (DataSnapshot j : dataSnapshot.getChildren()) {
                    Admin a = j.getValue(Admin.class);
                    if (a.getRole().equals("student") && a.getBranch().equals(t.getBranch()) && a.getCollege().equals(t.getCollege()) && a.getSec().equals(t.getSec()) && a.getYear().equals(t.getYear())) {
                        l.add(a);
                    }
                }

                m1 = new MultichooseAdapter(getContext(), l);
                rv.setAdapter(m1);

                m1.setMultiChoiceSelectionListener(new MultiChoiceAdapter.Listener() {
                    @Override
                    public void OnItemSelected(int selectedPosition, int itemSelectedCount, int allItemCount) {
                        attededlist.add(l.get(selectedPosition));
                    }

                    @Override
                    public void OnItemDeselected(int deselectedPosition, int itemSelectedCount, int allItemCount) {
                        attededlist.remove(l.get(deselectedPosition));
                    }

                    @Override
                    public void OnSelectAll(int itemSelectedCount, int allItemCount) {
                        Toast.makeText(getContext(), Integer.toString(itemSelectedCount) + "allselscted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnDeselectAll(int itemSelectedCount, int allItemCount) {
                        Toast.makeText(getContext(), Integer.toString(itemSelectedCount) + "deeaaalllselscted", Toast.LENGTH_SHORT).show();
                    }
                });
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Set<Admin> attededlistshow = new HashSet<Admin>();
                        attededlistshow.addAll(l);
                        attededlistshow.removeAll(attededlist);
                        ArrayList<Admin> outarr=new ArrayList<>();
                        outarr.addAll(attededlistshow);
                        if(!attededlistshow.isEmpty()) {
                            AlertDialog.Builder bui = new AlertDialog.Builder(getContext());
                            ViewGroup viewGroup = v.findViewById(android.R.id.content);
                            View dv = LayoutInflater.from(getContext()).inflate(R.layout.customdialoginpostattendence, viewGroup, false);
                            CustomDialogAdapterPostAttendence adapter = new CustomDialogAdapterPostAttendence(getContext(), outarr);
                            ListView listView = (ListView) dv.findViewById(R.id.listitemindialog);
                            listView.setAdapter(adapter);
                            bui.setPositiveButton("Post", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    postAttedence(attededlist,attededlistshow);
                                    Toast.makeText(getContext(), "posted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            bui.setView(dv);
                            AlertDialog alertDialog = bui.create();
                            alertDialog.show();
                        }
                        else{
                            Snackbar.make(v,"All Present !",Snackbar.LENGTH_LONG)
                                    .setAction("Post", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            postAttedence(attededlist,attededlistshow);
                                            Toast.makeText(getContext(), "posted"+day.format(d)+d, Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void postAttedence(final Set<Admin> li, final Set<Admin> opcent){
        FirebaseDatabase fdd=FirebaseDatabase.getInstance();
        DatabaseReference dbrr=fdd.getReference("Attendence");
        DatabaseReference dbpost=dbrr.child(year.format(d)+"/"+month.format(d)+"/"+week.format(d)+"/"+day.format(d)+"/"+t1.getCollege()+"/"+t1.getBranch()+"/"+t1.getYear()+"/"+t1.getSec()+"/"+t1.getHour()+"/");
        ArrayList<String> userlist=new ArrayList<String>();
        for(Admin i:li){
            userlist.add(i.getUserId());
        }

        AttendenceFormat f=new AttendenceFormat();
        f.setTimemillis(Long.toString(System.currentTimeMillis()));
        f.setStudents(userlist);
        f.setTeacherid(FirebaseAuth.getInstance().getUid());
        f.setTime(d.toString());

        dbpost.setValue(f)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        sendnotifications(li,opcent);
                    }
                });
    }
    private void sendnotifications(Set<Admin> arr,Set<Admin> opcent){
        FirebaseDatabase fdd=FirebaseDatabase.getInstance();
        DatabaseReference dbrr=fdd.getReference("notifications");
        for(Admin i:arr){
            NotificationClass n=new NotificationClass();
            n.setTeacherid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            n.setTimetable(t1);
            n.setUserid(i.getUserId());
            n.setStatus(true);
            n.setTime(new Date().toString());
            n.setTimemillis(Long.toString(System.currentTimeMillis()));
            DatabaseReference postattend=dbrr.child(i.getUserId()+"/"+t1.getHour());
            postattend.setValue(n);
        }
        for(Admin i:opcent){
            NotificationClass n=new NotificationClass();
            n.setTeacherid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            n.setTimetable(t1);
            n.setUserid(i.getUserId());
            n.setStatus(false);
            n.setTime(new Date().toString());
            n.setTimemillis(Long.toString(System.currentTimeMillis()));
            DatabaseReference postattend=dbrr.child(i.getUserId()+"/"+t1.getHour());
            postattend.setValue(n);
        }
    }
}
