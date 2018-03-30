package com.example.jfarina.gamesystem720.video.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.R;
import com.example.jfarina.gamesystem720.ViewProfileActivity;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Button touchVideos = findViewById(R.id.videos_button);
        touchVideos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VideosPageActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchMovies = findViewById(R.id.movies_button);
        touchMovies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MoviesActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchTvShows = findViewById(R.id.tv_shows_button);
        touchTvShows.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), TVShowsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
