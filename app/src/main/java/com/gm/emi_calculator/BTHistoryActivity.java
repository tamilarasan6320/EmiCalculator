package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gm.emi_calculator.Constant.Constant_Variable;
import com.gm.emi_calculator.Databasehelper.DB_BTHistory;
import com.gm.emi_calculator.adapter.AdapterBTHistory;
import com.gm.emi_calculator.model.ModelBTHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BTHistoryActivity extends AppCompatActivity {
    ArrayList<ModelBTHistory> f3832j;
    private AdapterBTHistory mAdapter;
    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;
    @BindView(R.id.clearall)
    Button ClearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_t_history);
        ButterKnife.bind((Activity) this);
        //setTitle(XmpMMProperties.HISTORY);
        mo12088s();
        ClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_BTHistory.getInstance(BTHistoryActivity.this).clearAllLog();
                Toast.makeText(BTHistoryActivity.this.getApplicationContext(), "Cleared Successfully", Toast.LENGTH_SHORT).show();
                BTHistoryActivity.this.mo12088s();
            }
        });
    }
    public void mo12088s() {
        ArrayList<ModelBTHistory> allHistory = DB_BTHistory.getInstance(this).getAllHistory();
        this.f3832j = allHistory;
        this.mAdapter = new AdapterBTHistory(this, allHistory, new AdapterBTHistory.OnItemClickListener() {
            public void onItemClick(ModelBTHistory modelBTHistory) {
                Intent intent = new Intent(Constant_Variable.BT_HISTORY);
                intent.putExtra(Constant_Variable.IS_EDIT, true);
                intent.putExtra(Constant_Variable.BT_HISTORY, modelBTHistory);
                LocalBroadcastManager.getInstance(BTHistoryActivity.this.getApplicationContext()).sendBroadcast(intent);
                finish();
            }
        });
        this.rvHistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvHistory.setItemAnimator(new DefaultItemAnimator());
        this.rvHistory.setAdapter(this.mAdapter);
    }
}