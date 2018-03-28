package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ViewController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

    public void touchRegister(View view) {
        String firstName, lastName, party, state;
        Integer electionYear;
        PollModel poll = new PollModel(this);
        EditText txtFirstName = findViewById(R.id.txtFirstName);
        EditText txtLastName =  findViewById(R.id.txtLastName);
        EditText txtParty =  findViewById(R.id.txtParty);
        EditText txtState =  findViewById(R.id.txtState);
        EditText txtElectionYear =  findViewById(R.id.txtElectionYear);
        firstName = txtFirstName.getText().toString();
        lastName = txtLastName.getText().toString();
        party = txtParty.getText().toString();
        state = txtState.getText().toString();
        electionYear = Integer.parseInt(txtElectionYear.getText().toString());
        Boolean isAdded = poll.insertData(firstName, lastName, party, state, electionYear);
        if (isAdded) {
            Toast.makeText(ViewController.this, "User is registered sucessfully", Toast.LENGTH_LONG).show();
            Log.d("Insertion", "poll data  is inserted  sucessfully");
            //An Intent is a messaging object you can use to request an action from another app component.
            Intent intent = new Intent(this, ListViewController.class);
            startActivity(intent);



        }
    }
}
