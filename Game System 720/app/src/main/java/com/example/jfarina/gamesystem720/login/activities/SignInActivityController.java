package com.example.jfarina.gamesystem720.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jfarina.gamesystem720.HomePageActivityController;
import com.example.jfarina.gamesystem720.R;
import com.example.jfarina.gamesystem720.model.and.db.directory.DatabaseHandler;

public class SignInActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText username = findViewById(R.id.username_txt);
        final EditText password = findViewById(R.id.password_txt);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button touchViewRegister = findViewById(R.id.loginRegisterButton);
        touchViewRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddUserActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HomePageActivityController.class);
                String userName = username.getText().toString();
                String userPass = password.getText().toString();
                Boolean chkUserPass = db.usernamePassword(userName, userPass);
                if (chkUserPass) {
                    Toast.makeText(getApplicationContext(), "Welcome " + userName,
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

