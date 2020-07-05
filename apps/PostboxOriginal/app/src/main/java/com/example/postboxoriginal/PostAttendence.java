package com.example.postboxoriginal;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PostAttendence extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewpager;
    FragmentActivity con;
    View v;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        con=(FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_post_attendence,container,false);
        viewpager =v.findViewById(R.id.viewpager);
        tabLayout =v.findViewById(R.id.tablay);
        viewpager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewpager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                        switch(position+1){
                            case 1:tab.setIcon(R.drawable.firsttab);break;
                            case 2:tab.setIcon(R.drawable.secondtab);break;
                            case 3:tab.setIcon(R.drawable.thirdtab);break;
                            case 4:tab.setIcon(R.drawable.fourthtab);break;
                            case 5:tab.setIcon(R.drawable.fifthtab);break;
                            case 6:tab.setIcon(R.drawable.sixthtab);break;
                            case 7:tab.setIcon(R.drawable.seventhtab);break;
                            case 8:tab.setIcon(R.drawable.eighthtab);break;
                            default: tab.setText("Tab"+(position+1));
                        }
                    }
                }).attach();

        return v;
    }
    private TabAdapter createCardAdapter() {

        TabAdapter adapter = new TabAdapter(con);
        return adapter;
    }

}
