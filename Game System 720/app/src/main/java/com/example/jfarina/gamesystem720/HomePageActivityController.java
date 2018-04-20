package com.example.jfarina.gamesystem720;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.friends.messages.activities.FriendsActivityController;
import com.example.jfarina.gamesystem720.login.activities.SignInActivityController;
import com.example.jfarina.gamesystem720.model.and.db.directory.DatabaseHandler;
import com.example.jfarina.gamesystem720.store.activities.StoreActivityController;
import com.example.jfarina.gamesystem720.video.activities.VideoActivity;


public class HomePageActivityController extends AppCompatActivity {
DatabaseHandler databaseHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Button touchSignOut = findViewById(R.id.logout_button);
        touchSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder diaSignOut = new AlertDialog.Builder(HomePageActivityController.this);
                diaSignOut.setCancelable(false);
                diaSignOut.setMessage("Do you wish to sign out?");
                diaSignOut.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he/she is allowed to exit from application
                        Intent myIntent = new Intent(getApplicationContext(), SignInActivityController.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(myIntent);
                    }
                });
                diaSignOut.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });

                AlertDialog alert = diaSignOut.create();
                alert.show();
            }
        });

        Button touchViewProfile = findViewById(R.id.profile_settings__button);
        touchViewProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewProfileActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchNotifcations = findViewById(R.id.notifications_button);
        touchNotifcations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NotificationActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchFriendsAndMessages = findViewById(R.id.friends_and_messages_button);
        touchFriendsAndMessages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FriendsActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchVideo = findViewById(R.id.videos_and_movies_button);
        touchVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VideoActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchStore = findViewById(R.id.store_button);
        touchStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchSettings = findViewById(R.id.settings_button);
        touchSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SettingsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you wish to sign out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he/she is allowed to exit from application
                Intent myIntent = new Intent(getApplicationContext(), SignInActivityController.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });

        AlertDialog alert=builder.create();
        alert.show();
    }
}