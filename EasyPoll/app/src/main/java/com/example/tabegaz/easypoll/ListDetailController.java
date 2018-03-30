package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ListDetailController extends AppCompatActivity {
    ArrayList<String> userInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_view);
        PollModel poll = new PollModel(this);
        userInfo = poll.getPollList();


        Intent intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        String lName = intent.getStringExtra("lastName");
        Log.d("First Name",fName);
        Log.d("Last Name",lName);


        userInfo = poll.getPollList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListDetailController.this, android.R.layout.simple_expandable_list_item_1, userInfo);
        ListView listView = findViewById(R.id.detailedListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListDetailController.this, userInfo.get(position).toString(),  Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListDetailController.this, ListDetailController.class);
                startActivity(intent);

            }
        });

    }


    }

