package com.example.jfarina.easypill.login.activity.controllers;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.jfarina.easypill.R;
import com.example.jfarina.easypill.model.and.db.directory.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivityController extends AppCompatActivity {
    EditText firstName, lastName, dateOfBirth, email, password, confirmPassword;
    String first, last, birth, emailAdd, passwd, conPasswd;
    DatabaseHandler db = new DatabaseHandler(this);
    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivityController.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    public void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Failed to create account.", Toast.LENGTH_SHORT).show();
        } else {
            if (db.insertUserData(emailAdd, passwd)) {
                onSignupSuccess();
            } else {
                Toast.makeText(this, "Failed to create account.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initialize() {
        first = firstName.getText().toString().trim();
        last = lastName.getText().toString().trim();
        birth = dateOfBirth.getText().toString().trim();
        emailAdd = email.getText().toString().trim();
        passwd = password.getText().toString().trim();
        conPasswd = confirmPassword.getText().toString().trim();
    }

    public boolean validate() {
        boolean valid = true;
        if (firstName.length() == 0 || firstName.length() > 32) {
            firstName.setError("Please enter a valid first name.");
            valid = false;
        }
        if (lastName.length() == 0 || lastName.length() > 32) {
            lastName.setError("Please enter a valid last name.");
            valid = false;
        }
        if (dateOfBirth.length() == 0 || TextUtils.isEmpty(dateOfBirth.getText().toString())) {
            dateOfBirth.setError("Please enter your date of birth.");
            valid = false;
        }
        if (email.length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
            email.setError("Please enter a valid Email Address.");
            valid = false;
        }
        if (!db.checkEmail(emailAdd))
        {
            email.setError("Email address is already in use.");
            valid = false;
        }
        if (password.length() == 0) {
            password.setError("Please enter password.");
            valid = false;
        }
        if (confirmPassword.length() == 0 || !passwd.equals(conPasswd)) {
            confirmPassword.setError("Please reenter password or make sure passwords match.");
            valid = false;
        }
        return valid;
    }

    public void onSignupSuccess() {
        Toast.makeText(getBaseContext(), "User account: " + first + " " + last + ", created.", Toast.LENGTH_SHORT).show();
        android.content.Intent myIntent = new android.content.Intent(getApplicationContext(), LoginActivityController.class);
        myIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }
}