package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AddSubscriptionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CardView subscriptionCardView;

    ArrayAdapter<String> adapter;
    List<Subscription> subscriptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        ImageView bttnBackArrowAddSub = findViewById(R.id.bttnBackArrowAddSub);
        Button bttnEdit = findViewById(R.id.bttnEdit);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subscriptionCardView = findViewById(R.id.subscriptionCardView);

        subscriptionList = new ArrayList<>();

        subscriptionList.add(
                new Subscription(
                        1,
                        "Netflix",
                        "Monthly",
                        10.99,
                        R.drawable.netflixicon,
                        R.color.pomegranate));
        subscriptionList.add(
                new Subscription(
                        2,
                        "Spotify Premium",
                        "Monthly",
                        6.99,
                        R.drawable.spotifyicon,
                        R.color.spotify));
        subscriptionList.add(
                new Subscription(
                        3,
                        "Google Play Music",
                        "Monthly",
                        9.99,
                        R.drawable.googleplaymusicicon,
                        R.color.amazonOrange));
        subscriptionList.add(
                new Subscription(
                        4,
                        "Apple Music",
                        "Monthly",
                        9.99,
                        R.drawable.itunesicon,
                        R.color.appleMusic));
        subscriptionList.add(
                new Subscription(
                        5,
                        "Xbox Live",
                        "Monthly",
                        9.99,
                        R.drawable.xboxicon,
                        R.color.xboxlive));
        subscriptionList.add(
                new Subscription(
                        6,
                        "Playstation Plus",
                        "Monthly",
                        9.99,
                        R.drawable.playstationicon,
                        R.color.playstaion));
        subscriptionList.add(
                new Subscription(
                        7,
                        "Hulu",
                        "Monthly",
                        7.99,
                        R.drawable.huluicon,
                        R.color.hulu));
        subscriptionList.add(
                new Subscription(
                        8,
                        "HBO",
                        "Monthly",
                        14.99,
                        R.drawable.hboicon,
                        R.color.hbo));

        subscriptionList.add(
                new Subscription(
                        9,
                        "1Password",
                        "Monthly",
                        2.99,
                        R.drawable.onepassword,
                        R.color.onepassword));
        subscriptionList.add(
                new Subscription(
                        10,
                        "500PX",
                        "Monthly",
                        4.99,
                        R.drawable.fivepx,
                        R.color.fivepx));

        subscriptionList.add(
                new Subscription(
                        11,
                        "Adobe CC",
                        "Monthly",
                        60.99,
                        R.drawable.adobecc,
                        R.color.alizarin));
        subscriptionList.add(
                new Subscription(
                        12,
                        "Amazon Prime",
                        "Monthly",
                        10.99,
                        R.drawable.amazonicon,
                        R.color.amazonOrange));
        subscriptionList.add(
                new Subscription(
                        13,
                        "AT&T",
                        "Monthly",
                        80.00,
                        R.drawable.ataticon,
                        R.color.blue));
        subscriptionList.add(
                new Subscription(
                        14,
                        "Adobe XD",
                        "Monthly",
                        9.99,
                        R.drawable.adobexdicon,
                        R.color.purple));
        subscriptionList.add(
                new Subscription(
                        15,
                        "Amazon AWS",
                        "Monthly",
                        2.00,
                        R.drawable.awsicon,
                        R.color.amazonAws));
        subscriptionList.add(
                new Subscription(
                        16,
                        "Audible",
                        "Monthly",
                        14.95,
                        R.drawable.audible,
                        R.color.audible));
        subscriptionList.add(
                new Subscription(
                        17,
                        "Apple Developer Program",
                        "Yearly",
                        100.0,
                        R.drawable.apple,
                        R.color.fullBlack));
        subscriptionList.add(
                new Subscription(
                        18,
                        "Asana",
                        "Monthly",
                        9.99,
                        R.drawable.asana,
                        R.color.asana));
        subscriptionList.add(
                new Subscription(
                        19,
                        "Avg Internet Security",
                        "yearly",
                        99.99,
                        R.drawable.avg,
                        R.color.avg));
        subscriptionList.add(
                new Subscription(
                        20,
                        "Bluehost",
                        "Monthly",
                        4.95,
                        R.drawable.bluehost,
                        R.color.bluehost));
        subscriptionList.add(
                new Subscription(
                        21,
                        "Bank Of America",
                        "Monthly",
                        9.99,
                        R.drawable.bankofamerica,
                        R.color.bankofamerica));
        subscriptionList.add(
                new Subscription(
                        22,
                        "Barclays",
                        "Monthly",
                        9.99,
                        R.drawable.barclay,
                        R.color.barclays));
        subscriptionList.add(
                new Subscription(
                        23,
                        "BT(British Telecom)",
                        "Monthly",
                        10.00,
                        R.drawable.bt,
                        R.color.bt));
        subscriptionList.add(
                new Subscription(
                        24,
                        "Bite(Lithuanian Telecom)",
                        "Monthly",
                        9.99,
                        R.drawable.bite,
                        R.color.bite));
        subscriptionList.add(
                new Subscription(
                        25,
                        "Blizzard Entertainment",
                        "Monthly",
                        12.99,
                        R.drawable.blizzard,
                        R.color.blizzard));
        subscriptionList.add(
                new Subscription(
                        26,
                        "Comcast",
                        "Monthly",
                        29.99,
                        R.drawable.comcast,
                        R.color.comcast));
        subscriptionList.add(
                new Subscription(
                        27,
                        "Crunchyroll",
                        "Monthly",
                        6.95,
                        R.drawable.crunchyroll,
                        R.color.crunchyroll));


        SubscriptionAdapter adapter = new SubscriptionAdapter(this, subscriptionList);
        recyclerView.setAdapter(adapter);



        bttnBackArrowAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSubscriptionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        bttnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSubscriptionActivity.this, SubscriptionEdit.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar, menu);


        return true;
    }
}
