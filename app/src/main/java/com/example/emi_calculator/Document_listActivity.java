package com.example.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Document_listActivity extends AppCompatActivity {


    LinearLayout mortigage_loan,bt_mortigage_loan,home_loan,bt_home_loan,business_loan,personal_loan;
    ImageButton Toolbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);


        mortigage_loan = findViewById(R.id.mortigage_loan);
        bt_mortigage_loan = findViewById(R.id.bt_mortigage_loan);
        home_loan = findViewById(R.id.home_loan);
        bt_home_loan = findViewById(R.id.bt_home_loan);
        business_loan = findViewById(R.id.business_loan);
        personal_loan = findViewById(R.id.personal_loan);

        Toolbtn = findViewById(R.id.toolbar);
        Toolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });



        personal_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","personal_loan");
                startActivity(intent);
            }
        });
        mortigage_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","mortigage_loan");
                startActivity(intent);
            }
        });
        bt_mortigage_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","bt_mortigage_loan");
                startActivity(intent);
            }
        });

        home_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","home_loan");
                startActivity(intent);
            }
        });
        bt_home_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","bt_home_loan");
                startActivity(intent);
            }
        });
        business_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Document_listActivity.this,DocumentActivity.class);
                intent.putExtra("loan","business_loan");
                startActivity(intent);
            }
        });
    }
}