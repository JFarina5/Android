package com.example.jfarina.easypill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jfarina.easypill.login.activity.controllers.LoginActivityController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView loadText = findViewById(R.id.loadingText);
        ImageView loadImage = findViewById(R.id.loadingImage);
        final Intent intent = new Intent(this, LoginActivityController.class);
        Animation loadAnim = AnimationUtils.loadAnimation(this,R.anim.animation);
        loadText.startAnimation(loadAnim);
        loadImage.startAnimation(loadAnim);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();

                }
            }
        };

        timer.start();

    }
}
