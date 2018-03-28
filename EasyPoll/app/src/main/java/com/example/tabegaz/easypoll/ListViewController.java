package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewController extends AppCompatActivity {
    ArrayList<String> candidateList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PollModel poll = new PollModel(this);
        setContentView(R.layout.activity_list_view);
        candidateList = poll.getName();
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, candidateList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
           //The AdapterView could be a ListView, GridView, Spinner, etc. The question mark inside the angle brackets indicates
           // that it could be any of them. This is called generics in Java.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ListViewController.this, candidateList.get(position).toString(),  Toast.LENGTH_LONG).show();
                //The intent is created inside another class, here an anonymous inner class OnClickListener. Thus this does not refer the instance of your Activity
                // (or Context) as intended but the instance of your anonymous inner class OnClickListener
                // so  I used ListViewController.this instead of this
                Intent intent = new Intent(ListViewController.this, ListDetailController.class);
                String[] tokens = candidateList.get(position).toString().split(" ");
                intent.putExtra("firstName", tokens[0]);
                intent.putExtra("lastName", tokens[1]);
                startActivity(intent);

            }
        });

    }
}
