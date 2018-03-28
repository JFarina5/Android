package com.example.jfarina.gamesystem720.store.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.HomePageActivityController;
import com.example.jfarina.gamesystem720.R;

public class StoreActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Button touchHome = findViewById(R.id.home_button);
        touchHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HomePageActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
