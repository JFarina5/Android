package com.example.jfarina.gamesystem720;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ListView viewList = findViewById(R.id.notification_list);
        TextView emptyText = findViewById(R.id.emptyText);
        viewList.setEmptyView(emptyText);
    }
}
