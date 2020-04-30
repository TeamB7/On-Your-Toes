<<<<<<< HEAD
package com.example.a5120app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(  this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
=======
package com.example.a5120app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(  this,StartActivity.class);
        startActivity(intent);
        finish();
    }
}
>>>>>>> 6774dfa8ce789244192f56c4d32a1d9beb5c7b16
