package com.example.jfarina.gamesystem720.store.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.HomePageActivityController;
import com.example.jfarina.gamesystem720.R;
import com.example.jfarina.gamesystem720.ViewProfileActivity;

public class StoreActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Button touchSearchGames = findViewById(R.id.search_games_button);
        touchSearchGames.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreGamesActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchSearchMovies = findViewById(R.id.search_movies_button);
        touchSearchMovies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreMoviesActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchSearchTV = findViewById(R.id.search_tv_shows_button);
        touchSearchTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreTVActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchSearchApplication = findViewById(R.id.search_applications_button);
        touchSearchApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreApplicationsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
