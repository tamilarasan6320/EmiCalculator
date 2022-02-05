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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

public class Cheack_eligibilityActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheack_eligibility);

        LinearLayout linearLayout = findViewById(R.id.design_bottom_sheetcl);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        ImageButton mButton = findViewById(R.id.toolbar);


        Button button = findViewById(R.id.calculate_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


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
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    
    {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, Cheack_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(Cheack_eligibilityActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }

        }

        return false;
    }
}