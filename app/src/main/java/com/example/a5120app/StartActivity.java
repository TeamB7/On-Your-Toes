package com.example.a5120app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}
