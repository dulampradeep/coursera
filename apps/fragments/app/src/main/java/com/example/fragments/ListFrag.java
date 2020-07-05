package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

interface ItSelected{
    void getSelected(int index);
}
public class ListFrag extends ListFragment {
    ItSelected i1;

    public ListFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        i1=(ItSelected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String [] data=getResources().getStringArray(R.array.pieces);
       /* ArrayList<String> data=new ArrayList<String>();
        data.add("1.first data item");
        data.add("2.second data item");
        data.add("3.third data item");*/
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data));
        if(this.getActivity().findViewById(R.id.layout_land)!=null)
            i1.getSelected(0);

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        i1.getSelected(position);
    }
}
