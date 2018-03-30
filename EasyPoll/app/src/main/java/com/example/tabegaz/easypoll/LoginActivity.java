package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final PollModel db = new PollModel(this);
        final EditText username = findViewById(R.id.usernameTxt);
        final EditText password = findViewById(R.id.passwordTxt);
        Button registerPageButton = findViewById(R.id.registerPageButton);
        registerPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RegisterActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewController.class);
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Boolean chkUserPass = db.usernamePassword(user,pass);
                if(chkUserPass){Toast.makeText(getApplicationContext(), "Successful Login",
                            Toast.LENGTH_SHORT).show();
                    startActivityForResult(myIntent, 0);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
