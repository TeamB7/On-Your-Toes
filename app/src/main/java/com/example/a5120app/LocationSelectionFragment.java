package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LocationSelectionFragment extends Fragment {
    private View locationSelectView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationSelectView = inflater.inflate(R.layout.activity_user_location, container, false);

        return locationSelectView;
    }
}
