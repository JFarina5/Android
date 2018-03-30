package com.example.jfarina.gamesystem720.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jfarina.gamesystem720.HomePageActivityController;
import com.example.jfarina.gamesystem720.R;

public class SignInActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }

    public void touchLogin(View view) {
        EditText username = findViewById(R.id.username_txt);
        EditText password = findViewById(R.id.password_txt);

        if(username.getText().toString().equals("player") &&
                password.getText().toString().equals("password"))
        {
            Intent intent = new Intent(this, HomePageActivityController.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Incorrect username and/or password, please try again.", Toast.LENGTH_LONG).show();
        }

    }
}
