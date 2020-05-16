package com.example.a5120app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;

public class App_Home extends Fragment {
    private View homeView;
    private TextView greetingTv;
    private CardView amenitiesCard, safetyCard, exerciseCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeView = inflater.inflate(R.layout.activity_app__home, container, false);
        greetingTv = homeView.findViewById(R.id.tv_greeting);
        amenitiesCard = homeView.findViewById(R.id.amenities_card);
        safetyCard = homeView.findViewById(R.id.safety_card);
        exerciseCard = homeView.findViewById(R.id.exercise_card);

        SharedPreferences sp = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String firstName = sp.getString("FirstName", null);
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MainActivity", MODE_PRIVATE);
//        String name = sharedPreferences.getString("userName", null);
        String greeting = "";
        if (firstName != null) {
            greeting = "Hi " + firstName;
        }
        greetingTv.setText(greeting);

        SharedPreferences exerciseSP = getContext().getSharedPreferences("Exercise", MODE_PRIVATE);
        int index = exerciseSP.getInt("Index", 0);
        if (index > 0){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < index; i++){
                str.append(exerciseSP.getString(String.valueOf(i), "")).append(". \n");
            }
            greetingTv.setText(str);
        }

        amenitiesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new LocationSelectionFragment();
                Bundle args = new Bundle();
                args.putString("page", "amenity");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
                DrawerLayout drawer = (DrawerLayout) homeView.findViewById(R.id.main_layout);
            }
        });

        safetyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new LocationSelectionFragment();
                Bundle args = new Bundle();
                args.putString("page", "safety");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
                DrawerLayout drawer = (DrawerLayout) homeView.findViewById(R.id.main_layout);
            }
        });

        exerciseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new SuggestActivityFragment();
                Bundle args = new Bundle();
                args.putString("page", "safety");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
                DrawerLayout drawer = (DrawerLayout) homeView.findViewById(R.id.main_layout);
            }
        });

        return homeView;
    }
}
