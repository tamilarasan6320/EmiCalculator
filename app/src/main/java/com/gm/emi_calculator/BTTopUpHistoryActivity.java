package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gm.emi_calculator.adapter.AdapterBTTopupHistory;
import com.gm.emi_calculator.model.ModelNewBTHistory;

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