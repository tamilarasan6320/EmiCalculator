package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutusActivity extends AppCompatActivity {
    TextView abouttxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        abouttxt = findViewById(R.id.abouttxt);

        ImageButton mButton = findViewById(R.id.toolbar);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        abouttxt.setText("Loanzzones is the Best Loan Assistance Company in Hyderabad providing all loan\n" +
                "services since 2008. Loanzzones is the online platform of KRISHNA RANJITH ANNAM\n" +
                "with great passion to serve the best loan products.\n" +
                "Loanzzones offers all best loan services like Home Loans, Mortgage Loans, Personal\n" +
                "Loans, MSME Loans &amp; Business Loans. Our Industry experts help you to get the best\n" +
                "loan products.\n" +
                "\n" +
                "Our goal at Loanzzones is to provide access to home loans, personal loans, mortgage\n" +
                "loans, business loans &amp; MSME loans at competitive interest rates.\n" +
                "\n" +
                "Digital Platform\n" +
                "Competitive Interest Rate\n" +
                "Door Step Service\n" +
                "Loan Analysis\n" +
                "Loan Advisors\n" +
                "Client Satisfaction");




    }


}