<<<<<<< HEAD
package com.example.a5120app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TestApi extends Fragment {

    private String searchString;
    private EditText editText;
    private TextView result;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_api, container, false);
        Button test_btn = (Button) view.findViewById(R.id.test_bt);
        editText = (EditText) view.findViewById(R.id.test_input);
        result = (TextView) view.findViewById(R.id.result);
        searchString = "\"Abbeyard, 3737\"";

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!editText.getText().toString().isEmpty()) {
                    searchString = "\"" + editText.getText().toString() + "\"";
                }
            }
        });
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result.setText(RestClient.getSuburbScore(searchString));
                GetResultAsyncTask getresult = new GetResultAsyncTask();
                getresult.execute();
            }

        });

        return view;
    }

    private class GetResultAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String plain = RestClient.getSuburbScore(searchString);
            return plain;
        }

        @Override
        protected void onPostExecute(String s) {
            result.setText(s);
        }
    }

}
=======
package com.example.a5120app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TestApi extends Fragment {

    private String searchString;
    private EditText editText;
    private TextView result;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_api, container, false);
        Button test_btn = (Button) view.findViewById(R.id.test_bt);
        editText = (EditText) view.findViewById(R.id.test_input);
        result = (TextView) view.findViewById(R.id.result);
        searchString = "\"Abbeyard, 3737\"";

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!editText.getText().toString().isEmpty()) {
                    searchString = "\"" + editText.getText().toString() + "\"";
                }
            }
        });
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result.setText(RestClient.getSuburbScore(searchString));
                GetResultAsyncTask getresult = new GetResultAsyncTask();
                getresult.execute();
            }

        });

        return view;
    }

    private class GetResultAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String plain = RestClient.getSuburbScore(searchString);
            return plain;
        }

        @Override
        protected void onPostExecute(String s) {
            result.setText(s);
        }
    }

}
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
