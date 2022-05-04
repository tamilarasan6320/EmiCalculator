package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.emi_calculator.helper.ApiConfig;
import com.example.emi_calculator.helper.Constant;
import com.example.emi_calculator.model.ModelNewBTHistory;
import com.example.emi_calculator.model.ROI;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Current_roi_interestActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    Button update;
    Activity activity;
    ROI modelROI = new ROI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_roi_interest);
        activity = Current_roi_interestActivity.this;

        ImageButton mButton = findViewById(R.id.toolbar);
        update = findViewById(R.id.update);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateROI();
            }
        });
        readAllData();



        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Car"));
        tabLayout.addTab(tabLayout.newTab().setText("Bike"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


        viewPager =(ViewPager)findViewById(R.id.view_pager);
        TabsAdapter2 tabsAdapter = new TabsAdapter2(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void readAllData() {
        ROI_SqliteManager dbHandler;
        dbHandler = new ROI_SqliteManager(activity);
        ArrayList<ROI> roi = dbHandler.readAllRoi();
        Log.d("ROI_ALL_DATA",roi.get(0).getBank_hfc());
    }

    private void updateROI()
    {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                ArrayList<ROI> rois = new ArrayList<>();
                                ROI group = g.fromJson(jsonObject1.toString(), ROI.class);
                                modelROI.setBank_hfc(group.getCategory());
                                modelROI.setBank_hfc(group.getBank_hfc());
                                modelROI.setBank_hfc(group.getLakhs_75());
                                modelROI.setBank_hfc(group.getLakhs_30_75());

                                ROI_SqliteManager dbHandler;
                                dbHandler = new ROI_SqliteManager(Current_roi_interestActivity.this);
                                dbHandler.addItem(modelROI);

                                Toast.makeText(activity, ""+group.getLakhs_30_75(), Toast.LENGTH_SHORT).show();
                                rois.add(group);
                            } else {
                                break;
                            }
                        }


                    }
                    else {
                        Log.d("TEAM_RESPONSE",""+jsonObject.getString(Constant.MESSAGE));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("TEAM_RESPONSE",""+e.getMessage());
                }
            }
        }, activity, Constant.PERSONAL_LIST, params, true);

    }

}