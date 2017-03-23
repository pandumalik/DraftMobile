package com.example.galan.tubes;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.galan.tubes.adaptertest.BackgroundTask;
import com.example.galan.tubes.adaptertest.DisplayList;
import com.example.galan.tubes.adaptertest.RecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class materi extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public materi() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("MATERI");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.fragment_materi, container, false);
        //BackgroundTask bc = new BackgroundTask();

        Intent myIntent = new Intent(getActivity(), DisplayList.class);
        startActivity(myIntent);
        return ll;
    }


}
