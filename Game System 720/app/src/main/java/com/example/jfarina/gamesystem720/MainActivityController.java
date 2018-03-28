package com.example.jfarina.gamesystem720;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jfarina.gamesystem720.login.activities.HomeActivityController;

public class MainActivityController extends AppCompatActivity {
    private static int LAUNCH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_controller);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent (MainActivityController.this, HomeActivityController.class);
                startActivity(homeIntent);
                finish();
            }

        },LAUNCH_TIME_OUT);
    }
}
