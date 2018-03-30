package com.example.jfarina.gamesystem720.friends.messages.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jfarina.gamesystem720.R;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ListView viewList = findViewById(R.id.messages_listview);
        TextView emptyText = findViewById(R.id.messages_empty_text);
        viewList.setEmptyView(emptyText);

        Button touchFriends = findViewById(R.id.friends_button);
        touchFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FriendsActivityController.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button touchCompose = findViewById(R.id.compose);
        touchCompose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ComposeMessageActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
