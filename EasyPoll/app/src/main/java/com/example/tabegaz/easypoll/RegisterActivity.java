package com.example.tabegaz.easypoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final PollModel db = new PollModel(this);
        final EditText username = findViewById(R.id.regUserText);
        final EditText password = findViewById(R.id.regPassTxt);
        final EditText cpassword = findViewById(R.id.regConPassTxt);

        Button touchRegister = findViewById(R.id.registerButton);
        touchRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String cpass = cpassword.getText().toString();
                if(user.equals("") || pass.equals("")||cpass.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",
                            Toast.LENGTH_SHORT).show();
                    if (!pass.equals(cpass)){
                            Toast.makeText(getApplicationContext(), "Passwords do not match",Toast.LENGTH_SHORT).show();
                        }
                    }
                else{
                    if(pass.equals(cpass)){
                        Boolean chkusername = db.checkUsername(user);
                        if (chkusername){
                            Boolean insert = db.insertUserData(user, pass);
                            if (insert){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Username is already in use",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
