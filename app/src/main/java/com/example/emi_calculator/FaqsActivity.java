package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class FaqsActivity extends AppCompatActivity {

    LinearLayout l1,l2,l3;
    TextView Text1,Text2,Text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        Text1 = findViewById(R.id.text1);
        Text2 = findViewById(R.id.text2);
        Text3 = findViewById(R.id.text3);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Text1.setVisibility(View.VISIBLE);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text2.setVisibility(View.VISIBLE);

            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text3.setVisibility(View.VISIBLE);

            }
        });



        ImageButton mButton = findViewById(R.id.toolbar);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


    }


}