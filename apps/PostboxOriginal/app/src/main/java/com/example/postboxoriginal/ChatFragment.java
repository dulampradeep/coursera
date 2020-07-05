package com.example.postboxoriginal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.postboxoriginal.R;

public class ChatFragment extends Fragment {
    ImageView iv;
    TextView tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_chat,container,false);
        iv=v.findViewById(R.id.scrapeiv);
        tv=v.findViewById(R.id.scrapetv);

        new CiscoAnswerFinder().execute(iv);
        return v;

    }
}
