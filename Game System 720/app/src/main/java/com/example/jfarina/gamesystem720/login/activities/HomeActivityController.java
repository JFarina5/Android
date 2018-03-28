package com.example.jfarina.gamesystem720.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.R;

public class HomeActivityController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button touchSignIn = findViewById(R.id.sign_in_button);
        touchSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SignInActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button touchCreateUser = findViewById(R.id.create_user_button);
        touchCreateUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddUserActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
