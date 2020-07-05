package com.example.contacts;

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
public class ListFrag extends Fragment {
    RecyclerView rev;
    RecyclerView.Adapter adp;
    RecyclerView.LayoutManager lm;
    View v;
    public ListFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_list, container, false);
        return v;
    }

    //@Override
    /*public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rev=v.findViewById(R.id.recycle);
        rev.setHasFixedSize(true);
        lm=new LinearLayoutManager(this.getActivity());
        rev.setLayoutManager(lm);
        adp=new PersonAdapter(this.getActivity(),ApplicationClass.p);
        rev.setAdapter(adp);
    }*/
}
