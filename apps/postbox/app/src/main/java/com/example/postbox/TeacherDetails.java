package com.example.postbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class TeacherDetails extends Fragment {
    RecyclerView rv,rv2;
    RecyclerView.Adapter rvadp,rvadp2;
    RecyclerView.LayoutManager rvlm,rvlm2;
    List<Admin> l1,l2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_teacher_details2,container,false);
        final TabHost tabhost=v.findViewById(R.id.tabhost);
        tabhost.setup();
        l1=null;
        TabHost.TabSpec spec=tabhost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("All");
        tabhost.addTab(spec);
        spec=tabhost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Students");
        tabhost.addTab(spec);
        rv=(RecyclerView)v.findViewById(R.id.recyclerviewteacher);
        rv2=(RecyclerView)v.findViewById(R.id.recyclerviewstudent);
        rv.setHasFixedSize(true);
        rvlm=new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvlm);
        final DataQueryBuilder dqb=DataQueryBuilder.create();
        dqb.setWhereClause("role = 'faculty'");
        Backendless.Data.of(Admin.class).find(dqb, new AsyncCallback<List<Admin>>() {
            @Override
            public void handleResponse(List<Admin> response) {
                l1=response;
                rvadp=new TeacherAdapter(getContext(),l1);
                rv.setAdapter(rvadp);
                Toast.makeText(getContext(),l1.get(0).getObjectId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getContext(),fault.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        rvadp=new TeacherAdapter(getContext(),l1);
        rv.setAdapter(rvadp);

        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                int i = tabhost.getCurrentTab();

                if (i == 0) {
                    rv.setHasFixedSize(true);
                    rvlm=new LinearLayoutManager(getContext());
                    rv.setLayoutManager(rvlm);
                    final DataQueryBuilder dqb=DataQueryBuilder.create();
                    dqb.setWhereClause("role = 'faculty'");
                    Backendless.Data.of(Admin.class).find(dqb, new AsyncCallback<List<Admin>>() {
                        @Override
                        public void handleResponse(List<Admin> response) {
                            l1=response;
                            rvadp=new TeacherAdapter(getContext(),l1);
                            rv.setAdapter(rvadp);
                            Toast.makeText(getContext(),l1.get(0).getObjectId(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getContext(),fault.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    rvadp=new TeacherAdapter(getContext(),l1);
                    rv.setAdapter(rvadp);

                }
                else if (i ==1) {
                    rv2.setHasFixedSize(true);
                    rvlm2=new LinearLayoutManager(getContext());
                    rv2.setLayoutManager(rvlm2);
                    final DataQueryBuilder dqb2=DataQueryBuilder.create();
                    dqb2.setWhereClause("role = 'student'");
                    Backendless.Data.of(Admin.class).find(dqb2, new AsyncCallback<List<Admin>>() {
                        @Override
                        public void handleResponse(List<Admin> response) {
                            l2=response;
                            rvadp2=new TeacherAdapter(getContext(),l2);
                            rv2.setAdapter(rvadp2);
                            Toast.makeText(getContext(),l2.get(0).getObjectId(),Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getContext(),fault.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    rvadp2=new TeacherAdapter(getContext(),l2);
                    rv2.setAdapter(rvadp2);
                }
            }
        });
        return v;
    }
}

