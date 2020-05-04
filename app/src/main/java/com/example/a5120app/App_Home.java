package com.example.a5120app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class App_Home extends Fragment {
    private View homeView;
    private TextView greetingTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeView = inflater.inflate(R.layout.activity_app__home, container, false);
        greetingTv = homeView.findViewById(R.id.tv_greeting);
        SharedPreferences sharedPreferences =  getContext().getSharedPreferences("MainActivity", MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", null);
        String greeting = "";
        if (name != null){
            greeting = "Hi " + name;
        }
        greetingTv.setText(greeting);
        return homeView;
    }
}
