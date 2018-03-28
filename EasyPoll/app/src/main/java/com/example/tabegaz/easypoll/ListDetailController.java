package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ListDetailController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_view);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        String lName = intent.getStringExtra("lastName");
        Log.d("First Name",fName);
        Log.d("Last Name",lName);


    }
}
