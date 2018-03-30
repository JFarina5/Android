package com.example.jfarina.gamesystem720.login.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jfarina.gamesystem720.R;


public class AddUserActivityController extends AppCompatActivity {
    private EditText firstName,lastName,dateOfBirth,username,email, password,confirmPassword;
    private String first, last, birth, user, emailAdd, passwd, conPasswd;

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
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Failed to create account.", Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSuccess();
        }
    }

    public void onSignupSuccess(){
        Toast.makeText(getBaseContext(), "User account: " + user + ", created." , Toast.LENGTH_SHORT ).show();
    }

    public boolean validate(){
        boolean valid = true;
        if(firstName.length() == 0 || firstName.length()>32){
            firstName.setError("Please enter a valid first name.");
            valid = false;
        }
        if(lastName.length() == 0 || lastName.length()>32){
            lastName.setError("Please enter a valid last name.");
            valid = false;
        }
        if(dateOfBirth.length() == 0){
            dateOfBirth.setError("Please enter your date of birth.");
            valid = false;
        }
        if(username.length() == 0){
            username.setError("Please enter a username.");
            valid = false;
        }
        if(email.length() == 0|| !Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            email.setError("Please enter a valid Email Address.");
            valid = false;
        }
        if(password.length() == 0){
            password.setError("Please enter password.");
            valid = false;
        }
        if(confirmPassword.length() == 0 || !passwd.equals(conPasswd)){
            confirmPassword.setError("Please reenter password or make sure passwords match.");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
        first = firstName.getText().toString().trim();
        last = lastName.getText().toString().trim();
        birth = dateOfBirth.getText().toString().trim();
        user = username.getText().toString().trim();
        emailAdd = email.getText().toString().trim();
        passwd = password.getText().toString().trim();
        conPasswd = confirmPassword.getText().toString().trim();
    }
}
