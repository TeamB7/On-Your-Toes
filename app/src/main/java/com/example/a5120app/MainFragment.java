package com.example.a5120app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

    //    private Button test_api;
    private Button btnLocation, btnGetInfo, btnCrimeLocation;
    private EditText edSuburb;
    private TextView safetyInfoTv, suburbTv, crimeLocTv;
    private String suburb, suburbAndPostcode, safetyInfo;
    private View mainView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_main, container, false);
        safetyInfoTv = (TextView) mainView.findViewById(R.id.score_tv);
        suburbTv = (TextView) mainView.findViewById(R.id.suburb_tv);
        crimeLocTv = (TextView) mainView.findViewById(R.id.tv_crime_loc);
        btnLocation = (Button) mainView.findViewById(R.id.btn_location);
        btnCrimeLocation = (Button) mainView.findViewById(R.id.btn_crime_loc);
        suburb = "";
        suburbAndPostcode = "";

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new MapsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
                DrawerLayout drawer = (DrawerLayout) mainView.findViewById(R.id.main_layout);
            }

        });

        btnCrimeLocation.setVisibility(View.GONE);
        btnCrimeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCrimeLocationAsyncTask getCrimeLocationAsyncTask = new GetCrimeLocationAsyncTask();
                getCrimeLocationAsyncTask.execute();
            }

        });

        edSuburb = (EditText) mainView.findViewById(R.id.ed_suburb);
        edSuburb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!edSuburb.getText().toString().isEmpty()) {
<<<<<<< HEAD
                    suburb = edSuburb.getText().toString().replace(" ", "_").trim();
=======
                    suburb = edSuburb.getText().toString().trim();
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
                }
            }
        });

        btnGetInfo = (Button) mainView.findViewById(R.id.btn_security);
        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suburb = edSuburb.getText().toString().trim();
                if (!suburb.equals("")) {
                    GetSuburbAsyncTask getSuburbAsyncTask = new GetSuburbAsyncTask();
                    getSuburbAsyncTask.execute();
<<<<<<< HEAD
//                    btnCrimeLocation.setVisibility(View.VISIBLE);
                } else {
                    suburbTv.setText("Your Suburb: Invalid");
                    safetyInfoTv.setText("Risk Score: null");
=======
                    btnCrimeLocation.setVisibility(View.VISIBLE);
                } else {
                    suburbTv.setText("Your Suburb: Invalid");
                    safetyInfoTv.setText("Safety Score: null");
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
                    btnCrimeLocation.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                }

            }
        });
//
//        test_api = (Button) mainView.findViewById(R.id.btn_test);
//        test_api.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = null;
//                fragment = new TestApi();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame, fragment);
//                fragmentTransaction.commit();
//                DrawerLayout drawer = (DrawerLayout) mainView.findViewById(R.id.main_layout);
//            }
//
//        });
//
        return mainView;
    }


    private class GetScoreAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String score = RestClient.getSuburbScore(suburbAndPostcode);
            String indicator = RestClient.getSuburbIndicator(suburbAndPostcode);
<<<<<<< HEAD
            return score;
=======
            return score + "   " + indicator;
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
        }

        @Override
        protected void onPostExecute(String s) {
<<<<<<< HEAD
            String newText = "Risk Score: " + s + " out of 10";
=======
            String newText = "Safety Score: " + s;
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
            safetyInfoTv.setText(newText);
        }
    }

    private class GetCrimeLocationAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = RestClient.getLocationPercentage(suburbAndPostcode);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            String newText = "Crime Location: " + s;
            crimeLocTv.setText(newText);
        }
    }

    private class GetSuburbAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String postcode = RestClient.getSuburbByAddress(suburb);
            return postcode;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!s.equals("")) {
                suburbAndPostcode = suburb + ", " + s;
                String newText = "Your Suburb: " + suburbAndPostcode;
                suburbTv.setText(newText);
                GetScoreAsyncTask getScoreAsyncTask = new GetScoreAsyncTask();
                getScoreAsyncTask.execute();

            } else {
                suburb = "";
                suburbAndPostcode = "";
<<<<<<< HEAD
                suburbTv.setText("Your Suburb: Invalid");
                safetyInfoTv.setText("Risk Score: null");
                btnCrimeLocation.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
=======
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
            }

        }
    }

}