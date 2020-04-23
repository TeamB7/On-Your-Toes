package com.example.a5120app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static android.text.TextUtils.isEmpty;

public class CreateUserActivity extends AppCompatActivity {
    private TextView tvUserName, tvPassWord, tvConfirm, tvDob,
            tvHeight, tvWeight, tvAddress, tvPostcode;
    private EditText edUserName, edPassWord, edConfirm, edHeight, edWeight,
             edPostcode, edAddress;
    private int year = 1980, month = 5, date = 5;
    private boolean validUserName = false, validCPassword = false,
            validDob = false, validPassword = false, validPostcode = false, validAddress = false,
            validHeight = false, validWeight = false;
    private String datePicked;
    private Button btnDob, btnSignup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        tvUserName = (TextView) findViewById(R.id.tv_UserName);
        tvHeight = (TextView) findViewById(R.id.tv_Height);
        tvWeight = (TextView) findViewById(R.id.tv_Weight);
        tvDob = (TextView) findViewById(R.id.tv_Dob);
        tvPassWord = (TextView) findViewById(R.id.tv_Password);
        tvConfirm = (TextView) findViewById(R.id.tv_CPassword);
        tvPostcode = (TextView) findViewById(R.id.tv_Postcode);
        tvAddress = (TextView) findViewById(R.id.tv_Address);


        edUserName = (EditText) findViewById(R.id.ed_UserName);
        edPassWord = (EditText) findViewById(R.id.ed_Password);
        edConfirm = (EditText) findViewById(R.id.ed_CPassword);
        edHeight = (EditText) findViewById(R.id.ed_Height);
        edWeight = (EditText) findViewById(R.id.ed_Weight);
        edPostcode = (EditText) findViewById(R.id.ed_Postcode);
        edAddress = (EditText) findViewById(R.id.ed_Address);

        btnDob = (Button) findViewById(R.id.btn_Dob);
        btnSignup = (Button) findViewById(R.id.btn_Signup);

        edUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!isEmpty(edUserName.getText().toString())) {
                        CheckExistAsyncTask checkRepeat = new CheckExistAsyncTask();
                        checkRepeat.execute(edUserName.getText().toString(), "userName");
                    } else {
                        tvUserName.setText("User Name Required");
                        tvUserName.setTextColor(0xFFFF4081);
                        validUserName = false;
                    }
                }
            }
        });

        edPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edPassWord.getText().toString())) {
                    validPassword = true;
                    tvPassWord.setTextColor(0xFF303F9F);
                    tvPassWord.setText("Password");
                } else {
                    tvPassWord.setText("Password Required");
                    tvPassWord.setTextColor(0xFFFF4081);
                    validPassword = false;
                }
            }
        });

        edConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!isEmpty(edConfirm.getText().toString())) {
                        if (edConfirm.getText().toString().equals(edPassWord.getText().toString())) {
                            tvConfirm.setText("Confirm Password");
                            tvConfirm.setTextColor(0xFF303F9F);
                            validCPassword = true;
                        } else {
                            tvConfirm.setText("Confirm Password Not Match");
                            tvConfirm.setTextColor(0xFFFF4081);
                            validCPassword = false;
                        }
                    } else {
                        tvConfirm.setText("Confirm Password Required");
                        tvConfirm.setTextColor(0xFFFF4081);
                        validCPassword = false;
                    }
                }
            }
        });

        edPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edPassWord.getText().toString())) {
                    validPassword = true;
                    tvPassWord.setTextColor(0xFF303F9F);
                    tvPassWord.setText("Password");
                } else {
                    tvPassWord.setText("Password Required");
                    tvPassWord.setTextColor(0xFFFF4081);
                    validPassword = false;
                }
            }
        });

        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateUserActivity.this, dateListener, year, month, date).show();
            }
        });

        edPostcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edPostcode.getText().toString())) {
                    if (edPostcode.getText().toString().matches("\\d+")) {
                        validPostcode = true;
                        tvPostcode.setTextColor(0xFF303F9F);
                        tvPostcode.setText("Postcode");
                    } else {
                        tvPostcode.setText("Invalid Postcode");
                        tvPostcode.setTextColor(0xFFFF4081);
                        validPostcode = false;
                    }
                } else {
                    tvPostcode.setText("Postcode Required");
                    tvPostcode.setTextColor(0xFFFF4081);
                    validPostcode = false;
                }
            }
        });

        edAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edAddress.getText().toString())) {
                    validAddress = true;
                    tvAddress.setTextColor(0xFF303F9F);
                    tvAddress.setText("Address");
                } else {
                    tvAddress.setText("Address Required");
                    tvAddress.setTextColor(0xFFFF4081);
                    validAddress = false;
                }
            }
        });

        edHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edHeight.getText().toString())) {
                    if (edHeight.getText().toString().matches("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$")) {
                        validHeight = true;
                        tvHeight.setTextColor(0xFF303F9F);
                        tvHeight.setText("Height(m)");
                    } else {
                        tvHeight.setText("Invalid Height");
                        tvHeight.setTextColor(0xFFFF4081);
                        validHeight = false;
                    }
                } else {
                    tvHeight.setText("Height Required");
                    tvHeight.setTextColor(0xFFFF4081);
                    validHeight = false;
                }
            }
        });

        edWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isEmpty(edWeight.getText().toString())) {
                    if (edWeight.getText().toString().matches("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$")) {
                        validWeight = true;
                        tvWeight.setTextColor(0xFF303F9F);
                        tvWeight.setText("Weight(kg)");
                    } else {
                        tvWeight.setText("Invalid Weight");
                        tvWeight.setTextColor(0xFFFF4081);
                        validWeight = false;
                    }
                } else {
                    tvWeight.setText("Weight Required");
                    tvWeight.setTextColor(0xFFFF4081);
                    validWeight = false;
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validUserName && validCPassword && validDob && validPassword
                        && validPostcode && validAddress && validHeight && validWeight) {
                    CreateUserAsyncTask createUser = new CreateUserAsyncTask();
                    createUser.execute();
                }
            }
        });

    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int newYear, int newMonth, int newDate) {
            year = newYear;
            month = newMonth;
            date = newDate;
            String displayDate = date + "/" + Integer.toString(month + 1) + "/" + year;
            Date temp = Time.toDate(displayDate, "dd/MM/yyyy");
            if (temp.after(Time.getCurrentDate())) {
                tvDob.setText("Incorrect Date");
                tvDob.setTextColor(0xFFFF4081);
                validDob = false;
            } else {
                String strDay = "";
                String strMonth = "";
                if (date < 10)
                    strDay = "0" + date;
                else
                    strDay = date + "";
                if (month + 1 < 10)
                    strMonth = "0" + Integer.toString(month + 1);
                else
                    strMonth = month + "";

                datePicked = year + strMonth + strDay;
                btnDob.setText(displayDate);
                tvDob.setText("Date of Birth: ");
                tvDob.setTextColor(0xFF303F9F);
                validDob = true;
            }
        }
    };

    // String signUpDate, Integer userId, String name, String dob, String address, String postcode, String weight, String height
    private class CreateUserAsyncTask extends AsyncTask<Void, Void, UserRest> {
        @Override
        protected UserRest doInBackground(Void... params) {
            int credId = RestClient.getMaxId("cred") + 1;
            int userId = RestClient.getMaxId("user") + 1;

            UserRest userRest = new UserRest(
                    Time.getCurrentFormatedDate(),
                    userId,
                    edUserName.getText().toString(),
                    datePicked,
                    edAddress.getText().toString(),
                    edPostcode.getText().toString(),
                    edHeight.getText().toString(),
                    edWeight.getText().toString()
            );

            RestClient.createUser(userRest.toString());

            return userRest;
        }

        @Override
        protected void onPostExecute(UserRest userRest) {
            showText("Welcome, " + edUserName.getText().toString());
            Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
            intent.putExtra("user", userRest);
            startActivity(intent);
            finish();
        }
    }

    public void showText(String string) {
        Toast.makeText(CreateUserActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    private class CheckExistAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            if (RestClient.isExist(params[0], params[1]))
                return params[1] + "Exist";
            else
                return params[1] + "NotExist";
        }

        @Override
        protected void onPostExecute(String exist) {
            switch (exist) {
                case "userNameExist":
                    tvUserName.setText("Repeated Name");
                    tvUserName.setTextColor(0xFFFF4081);
                    validUserName = false;
                    break;
                case "userNameNotExist":
                    tvUserName.setText("User Name");
                    tvUserName.setTextColor(0xFF303F9F);
                    validUserName = true;
                    break;
            }
        }
    }
}
