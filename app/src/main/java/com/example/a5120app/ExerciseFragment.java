package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ExerciseFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercise, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String exercise = bundle.getString("exercise", null);
            Toast.makeText(getContext(), exercise, Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
