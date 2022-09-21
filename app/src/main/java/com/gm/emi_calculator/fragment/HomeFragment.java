package com.gm.emi_calculator.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gm.emi_calculator.R;
import com.gm.emi_calculator.ROI_SqliteManager;
import com.gm.emi_calculator.adapter.ROIAdapter;
import com.gm.emi_calculator.model.ROI;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    View root;
    Activity activity;
    ROIAdapter roiAdapter;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_home, container, false);

        activity = getActivity();
        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        ROI_SqliteManager dbHandler;
        dbHandler = new ROI_SqliteManager(activity);
        ArrayList<ROI> roi = dbHandler.readAllRoi("Home");

        roiAdapter = new ROIAdapter(activity, roi);
        recyclerView.setAdapter(roiAdapter);
        return root;


    }
}