package com.example.a5120app;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;

public class ChangeAddressFragment extends Fragment {
    private Button submitBtn, cancelBtn;
    private EditText addressEd;
    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_change_address, container, false);
        submitBtn = view.findViewById(R.id.submit_change_btn);
        cancelBtn = view.findViewById(R.id.cancel_change_btn);
        addressEd = view.findViewById(R.id.ed_change_suburb);

        addressEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!addressEd.getText().toString().isEmpty()) {
                    address = addressEd.getText().toString();
                } else {
                    Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = addressEd.getText().toString();
                ChangeAddressAsync changeAddressAsync = new ChangeAddressAsync();
                changeAddressAsync.execute();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new App_Home();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();

            }
        });


        return view;
    }

    private class ChangeAddressAsync extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return RestClient.getSuburbByAddress(address);
        }

        @Override
        protected void onPostExecute(String s) {
            if (!s.equals("")) {
                SharedPreferences sp = getContext().getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor Ed = sp.edit();
                String change = address.substring(0, 1).toUpperCase() + address.substring(1);
                Ed.putString("Address", change);
                Ed.commit();
                Fragment fragment = null;
                fragment = new App_Home();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
//                DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.main_layout);
            } else {
                Toast.makeText(getContext(), "Suburb is invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
