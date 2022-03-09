package com.example.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.Databasehelper.DB_BTHistory;
import com.example.emi_calculator.adapter.AdapterBTHistory;
import com.example.emi_calculator.adapter.AdapterBTTopupHistory;
import com.example.emi_calculator.model.ModelBTHistory;
import com.example.emi_calculator.model.ModelNewBTHistory;

import java.util.ArrayList;

public class BTTopUpHistoryActivity extends AppCompatActivity {
    ArrayList<ModelNewBTHistory> modelBTHistoryArrayList;
    private AdapterBTTopupHistory mAdapter;
    RecyclerView rvHistory;
    Button clearall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bttop_up_history);

        clearall = findViewById(R.id.clearall);
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteManager dbHandler;
                dbHandler = new SqliteManager(BTTopUpHistoryActivity.this);
                dbHandler.deleteitem();
                Viewdata();
            }
        });

        rvHistory = findViewById(R.id.rvHistory);
        Viewdata();
    }

    private void Viewdata() {
        SqliteManager dbHandler;
        dbHandler = new SqliteManager(BTTopUpHistoryActivity.this);
        ArrayList<ModelNewBTHistory> allHistory = dbHandler.readAllItems();
        this.modelBTHistoryArrayList = allHistory;
        this.mAdapter = new AdapterBTTopupHistory(this, allHistory);
        this.rvHistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvHistory.setItemAnimator(new DefaultItemAnimator());
        this.rvHistory.setAdapter(this.mAdapter);
    }
}