package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class FaqsActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    LinearLayout l1,l2,l3;
    TextView Text1,Text2,Text3;
    private DrawerLayout drawer;

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

                drawer.openDrawer(GravityCompat.START);

            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(FaqsActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(FaqsActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(FaqsActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(FaqsActivity.this, Cheack_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(FaqsActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(FaqsActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(FaqsActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(FaqsActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(FaqsActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(FaqsActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }

        }

        return false;
    }
}