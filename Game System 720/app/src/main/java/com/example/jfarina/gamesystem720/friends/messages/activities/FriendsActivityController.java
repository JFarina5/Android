package com.example.jfarina.gamesystem720.friends.messages.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jfarina.gamesystem720.R;
import com.example.jfarina.gamesystem720.ViewProfileActivity;

public class FriendsActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ListView viewList = findViewById(R.id.friends_listview);
        TextView emptyText = findViewById(R.id.friends_empty_text);
        viewList.setEmptyView(emptyText);

        Button touchMessages = findViewById(R.id.messages_button);
        touchMessages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MessagesActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
