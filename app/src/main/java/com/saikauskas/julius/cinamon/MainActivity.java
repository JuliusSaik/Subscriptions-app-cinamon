package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View ivAddSubscription = findViewById(R.id.ivAddSubscription);
        ImageView ivSettingsIcon = findViewById(R.id.ivSettingsIcon);

        ivAddSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSubscriptionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivSettingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity  .class);
                startActivity(intent);
                finish();
            }
        });

    }
}
