package com.example.a5120app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

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

        TextView dateTV1 = homeView.findViewById(R.id.date_tv_1);
        TextView dateTV2 = homeView.findViewById(R.id.date_tv_2);
        TextView dateTV3 = homeView.findViewById(R.id.date_tv_3);
        TextView nameTV1 = homeView.findViewById(R.id.name_tv_1);
        TextView nameTV2 = homeView.findViewById(R.id.name_tv_2);
        TextView nameTV3 = homeView.findViewById(R.id.name_tv_3);

        SharedPreferences exerciseSP = getContext().getSharedPreferences("Exercise", MODE_PRIVATE);
//        exerciseSP.edit().clear().commit();

        int index = exerciseSP.getInt("Index", 0);

        if (index >= 1) {
            try {
                JSONArray exerciseJson = new JSONArray(exerciseSP.getString(String.valueOf(0), ""));
                nameTV1.setText(exerciseJson.getString(0));
                dateTV1.setText(exerciseJson.getString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (index >= 2) {
            try {
                JSONArray exerciseJson = new JSONArray(exerciseSP.getString(String.valueOf(1), ""));
                nameTV1.setText(exerciseJson.getString(0));
                dateTV1.setText(exerciseJson.getString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (index == 3) {
            try {
                JSONArray exerciseJson = new JSONArray(exerciseSP.getString(String.valueOf(2), ""));
                nameTV1.setText(exerciseJson.getString(0));
                dateTV1.setText(exerciseJson.getString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
