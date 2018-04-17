package com.example.jfarina.easypill.login.activity.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jfarina.easypill.HomePageActivityController;
import com.example.jfarina.easypill.R;
import com.example.jfarina.easypill.model.and.db.directory.DatabaseHandler;

public class LoginActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText email = findViewById(R.id.email_txt);
        final EditText password = findViewById(R.id.password_txt);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Method for moving to register page from login page once "No Account" button is selected
        Button touchViewRegister = findViewById(R.id.loginRegisterButton);
        touchViewRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RegisterActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HomePageActivityController.class);
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();
                Boolean chkUserPass = db.usernamePassword(userEmail, userPass);
                if (chkUserPass) {
                    Toast.makeText(getApplicationContext(), "Successful Login",
                            Toast.LENGTH_SHORT).show();
                    startActivityForResult(myIntent, 0);

                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
