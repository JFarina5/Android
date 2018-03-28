package com.example.jfarina.gamesystem720;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jfarina.gamesystem720.friends.messages.activities.FriendsActivityController;
import com.example.jfarina.gamesystem720.login.activities.HomeActivityController;
import com.example.jfarina.gamesystem720.store.activities.StoreActivityController;

public class HomePageActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button touchFriendsAndMessages = findViewById(R.id.friends_and_messages_button);
        touchFriendsAndMessages.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FriendsActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button touchStore = findViewById(R.id.store_button);
        touchStore.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StoreActivityController.class);
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
                Intent myIntent = new Intent(getApplicationContext(), HomeActivityController.class);
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

