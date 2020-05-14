package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SuggestActivityFragment extends Fragment {
    private ArrayList<String> exerciseArray;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indoor, container, false);

        exerciseArray = new ArrayList<String>();
        addData();
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.listview_row, exerciseArray);

        listView = (ListView) view.findViewById(R.id.exercise_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Fragment fragment = null;
                fragment = new ExerciseFragment();
                Bundle args = new Bundle();
                args.putString("exercise", value);
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void addData() {
        exerciseArray.add("Arnold Press");
        exerciseArray.add("Bicep Curl");
        exerciseArray.add("Bicycle Crunch");
        exerciseArray.add("Bounds");
        exerciseArray.add("Box Toe Touch");
        exerciseArray.add("Burpee");
        exerciseArray.add("Butt Kickers");
        exerciseArray.add("Calf Raise");
        exerciseArray.add("Chest Press");
        exerciseArray.add("Close to Wide Grip Burnout");
        exerciseArray.add("Donkey Kick");
        exerciseArray.add("Fire Hydrant");
        exerciseArray.add("Flutter Kick");
        exerciseArray.add("Glute Bridge");
        exerciseArray.add("Glute Bridge March");
        exerciseArray.add("High Knees");
        exerciseArray.add("Jump Lunges");
        exerciseArray.add("Jump Rope");
        exerciseArray.add("Jumping Jacks");
        exerciseArray.add("Knee Drive");
        exerciseArray.add("Leg Raise");
        exerciseArray.add("Literally Just Jumping");
        exerciseArray.add("Lying Leg Raises");
        exerciseArray.add("Plank");
        exerciseArray.add("Pushup");
        exerciseArray.add("Reverse Crunches");
        exerciseArray.add("Seal Jacks");
        exerciseArray.add("Side Arm / Lateral Raise");
        exerciseArray.add("Side Lunge");
        exerciseArray.add("Side Plank");
        exerciseArray.add("Skaters");
        exerciseArray.add("Skipping");
        exerciseArray.add("Squat");
        exerciseArray.add("Squat to Lateral Leg Lift");
        exerciseArray.add("Standing Glute Kickbaks");
        exerciseArray.add("Standing Oblique Crunch");
        exerciseArray.add("Sumo Squat");
        exerciseArray.add("Superman");
        exerciseArray.add("Touchdown");
        exerciseArray.add("Tricep Overhead Press");
        exerciseArray.add("Twisted Mountain Climbers");
        exerciseArray.add("Weighted Jumping Jacks");
        exerciseArray.add("Weighted Punches");
        exerciseArray.add("Deadbug");
    }

}
