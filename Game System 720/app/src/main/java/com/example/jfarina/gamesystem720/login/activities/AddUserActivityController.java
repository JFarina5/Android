package com.example.jfarina.gamesystem720.login.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jfarina.gamesystem720.R;
import com.example.jfarina.gamesystem720.model.and.db.directory.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddUserActivityController extends AppCompatActivity {
    private EditText firstName,lastName,dateOfBirth,username,email, password,confirmPassword;
    private String first, last, birth, user, emailAdd, passwd, conPasswd;
    Calendar myCalendar = Calendar.getInstance();
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                new DatePickerDialog(AddUserActivityController.this, date, myCalendar
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
            if (db.insertUserData(user, passwd)) {
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
        user = username.getText().toString().trim();
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
        if (username.length() == 0) {
            username.setError("Please enter password.");
            valid = false;
        }
        if (!db.checkUsername(user))
        {
            username.setError("Username is already in use.");
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
        Toast.makeText(getBaseContext(), "User account: " + user + ", created.", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getApplicationContext(), SignInActivityController.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

}
