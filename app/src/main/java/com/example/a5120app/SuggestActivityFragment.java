package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SuggestActivityFragment extends Fragment {
    private String[] name, type;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indoor, container, false);

        addData();
        AdapterIndoor adapter = new AdapterIndoor(getActivity(), name, type);

        listView = (ListView) view.findViewById(R.id.exercise_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                String value = name[+position];
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

    //    Bicycle Crunch
//    Bounds
//            Burpee
//    Butt Kickers
//    Flutter Kick
//    High Knees
//    Jump Lunges
//    Jump Rope
//    Jumping Jacks
//    Knee Drive
//    Leg Raise
//    Literally Just Jumping
//    Lying Leg Raises
//            Plank
//    Pushup
//    Reverse Crunches
//    Seal Jacks
//    Side Plank
//    Skaters
//            Skipping
//    Superman
//            Touchdown
//    Mountain Climbers
//    Deadbug
    private void addData() {
        name = new String[]{"Bicycle Crunch", "Bounds", "Burpee", "Butt Kickers",
                "Flutter Kick", "High Knees", "Jump Lunges", "Jump Rope",
                "Jumping Jacks", "Knee Drive", "Leg Raise", "Literally Just Jumping",
                "Lying Leg Raises", "Plank", "Pushup", "Reverse Crunches", "Seal Jacks",
                "Side Plank", "Skaters", "Skipping", "Superman", "Touchdown",
                "Mountain Climbers", "Deadbug"};
        type = new String[]{"Strength", "Cardio, Laps", "Plyo", "Cardio",
                "Strength", "Cardio", "Plyo, Cardio", "Cardio", "Cardio",
                "Plyo", "Strength", "Plyo", "Strength", "Strength", "Strength",
                "Strength", "Cardio", "Strength", "Cardio", "Cardio, Laps",
                "Strength", "Plyo", "Cardio", "Strength"};
    }

}
