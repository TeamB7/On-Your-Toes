package com.example.a5120app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class User_location extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView;
    String [] Suburb_names={"abc","aaa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Set Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_user_location);

//code for auto complete
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_suburb);
//        Suburb_names=getResources().getStringArray(R.array.suburb_names); // get strings from db here
        ArrayAdapter<String> auto_adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Suburb_names);
        autoCompleteTextView.setAdapter(auto_adapter);
    }


}
