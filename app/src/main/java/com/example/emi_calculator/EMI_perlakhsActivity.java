package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class EMI_perlakhsActivity extends AppCompatActivity{
    WebView activity_wb_common;
    Button Calculatebtn;
    EditText etInterestRate,etTenure;
    TextView emitxt;
    Utility_CalculateEMI utility_calculateEMI;
    




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_m_i_perlakhs);

        utility_calculateEMI = new Utility_CalculateEMI();
        ImageButton mButton = findViewById(R.id.toolbar);
        Calculatebtn  = findViewById(R.id.calculate_btn);
        etInterestRate  = findViewById(R.id.etInterestRate);
        emitxt  = findViewById(R.id.emitxt);
        etTenure  = findViewById(R.id.etTenure);
        WebView webView = (WebView) findViewById(R.id.activity_wb_common);
        this.activity_wb_common = webView;
        webView.getSettings().setLoadWithOverviewMode(true);
        this.activity_wb_common.getSettings().setBuiltInZoomControls(true);
        this.activity_wb_common.loadUrl("file:///android_asset/html/emiperlakh.html");
        this.activity_wb_common.requestFocus();
        this.activity_wb_common.setLongClickable(false);





        Calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etInterestRate.getText().toString())){
                    Toast.makeText(EMI_perlakhsActivity.this, "Enter Interest Rate", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(etTenure.getText().toString())){
                    Toast.makeText(EMI_perlakhsActivity.this, "Enter Tenure", Toast.LENGTH_SHORT).show();
                }
                else{
                    Double valueOf2 = Double.valueOf(Double.parseDouble(etInterestRate.getText().toString().replaceAll(",", "")));

                    if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
                        Toast.makeText(EMI_perlakhsActivity.this, "Enter the value between 0.1 to 99.99", Toast.LENGTH_SHORT).show();
                    }else {
                        calculate();
                    }

                }



            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });




    }

    private void calculate()
    {
        String emiamount = utility_calculateEMI.getEmiamount("100000", etTenure.getText().toString(), etInterestRate.getText().toString(), "0");

        emitxt.setText("EMI = ₹ " + Constant_CurrencyFormat.rupeeFormat(emiamount).trim());


    }


}