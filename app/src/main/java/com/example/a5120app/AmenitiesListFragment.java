package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class AmenitiesListFragment extends Fragment {
    private View amenitiesView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

//        "Aerobics",
//        "Badminton",
//        "Callisthenics",
//        "Carpet Bowls",
//        "Croquet",
//        "Cycling",
//        "Dancing",
//        "Disk Golf",
//        "Fitness / Gymnasium Workouts",
//        "Golf",
//        "Open Space",
//        "Swimming",
//        "Table Tennis",
//        "Ten Pin Bowling",
//        "Tennis (Outdoor)",
//        "Wheelchair Sports"

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amenitiesView = inflater.inflate(R.layout.fragment_amenities, container, false);
        recyclerView = amenitiesView.findViewById(R.id.lv_amenities);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("Aerobics");
        dataSet.add("Badminton");
        dataSet.add("Callisthenics");
        dataSet.add("Croquet");
        dataSet.add("Dancing");
        dataSet.add("Fitness");
        dataSet.add("Golf");
        dataSet.add("Open Space");
        dataSet.add("Swimming");
        dataSet.add("Tennis");
        dataSet.add("Wheelchair Sports");
        adapter = new MyAdapter(dataSet);
        recyclerView.setAdapter(adapter);
        return amenitiesView;
    }
}
