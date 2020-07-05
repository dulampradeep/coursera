package com.example.carowner;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class list extends Fragment {
    RecyclerView rv;
    RecyclerView.Adapter adp;
    RecyclerView.LayoutManager lm;
    View v;
    public list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v=inflater.inflate(R.layout.fragment_list, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv=v.findViewById(R.id.recycle);
        rv.setHasFixedSize(true);
        adp=new CarAdapter(this.getActivity(),CarApplication.n);
        rv.setAdapter(adp);
        lm=new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(lm);
    }
}
