package com.example.a5120app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.text.TextUtils.isEmpty;

public class CreateUserActivity extends Activity {
    private EditText edUserName, edLastName;
    private View createUserView;
    private Button createBtn;
    private String firstName = "", lastName = "";
    private SharedPreferences sp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        edUserName = findViewById(R.id.ed_UserFirstName);
        edLastName = findViewById(R.id.ed_UserLastName);
        createBtn = findViewById(R.id.button);

        edUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!isEmpty(edUserName.getText().toString())) {
                        firstName = edUserName.getText().toString();
                    }
                }
            }
        });

        edLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!isEmpty(edLastName.getText().toString())) {
                        lastName = edLastName.getText().toString();
                    }
                }
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             SharedPreferences.Editor Ed = sp.edit();
                                             Ed.putString("FirstName", firstName);
                                             Ed.putString("LastName", lastName);
                                             Ed.commit();
                                             Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
//                                             intent.putExtra("user", (Parcelable) sp);
                                             startActivity(intent);
                                             finish();
                                         }
                                     }
        );

        sp = getSharedPreferences("Login", MODE_PRIVATE);

    }

}
