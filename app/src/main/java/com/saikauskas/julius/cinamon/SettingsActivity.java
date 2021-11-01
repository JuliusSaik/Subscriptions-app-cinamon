package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView bttnBackArrowSettings = findViewById(R.id.bttnBackArrowSettings);
        TextView tvLogOut = findViewById(R.id.tvLogOut);


        bttnBackArrowSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChooseLoginSignUp.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
                FirebaseAuth.getInstance().signOut();
            }
        });
    }
}
