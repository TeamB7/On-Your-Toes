package com.example.a5120app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CrimeLocationFragment extends Fragment {
//    private Button myLocBtn, submitLocBtn;
//    private AutoCompleteTextView suburbACText;
//    private EditText suburbEd;
//    private String address = "";
//    private View view;
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_user_location, container, false);
//
//        myLocBtn = view.findViewById(R.id.use_my_loc_btn);
//        submitLocBtn = view.findViewById(R.id.submit_loc_btn);
//        suburbEd = view.findViewById(R.id.ed_suburb);
//
//
//        suburbEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!suburbEd.getText().toString().isEmpty()) {
//                    address = suburbEd.getText().toString();
//                } else {
//                    Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        myLocBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Fragment fragment = null;
//                        fragment = new AmenitiesListFragment();
//                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.content_frame, fragment);
//                        fragmentTransaction.commit();
//                        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.main_layout);
//                    }
//                }
//        );
//
//
//        submitLocBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (address != "") {
//                            Fragment fragment = null;
//                            fragment = new AmenitiesListFragment();
//                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.content_frame, fragment);
//                            fragmentTransaction.commit();
//                            DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.main_layout);
//                        }else{
//                            Toast.makeText(getContext(), "Address cannot be empty", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        return view;
//    }
}
