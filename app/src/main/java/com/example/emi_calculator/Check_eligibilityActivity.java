package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

public class Check_eligibilityActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private BottomSheetBehavior bottomSheetBehavior;
    ImageButton ToolIcon;
    Button calbtn;
    LinearLayout linearLayout;
    EditText edSalary, edExisting
            , edNewPossibleEmi, edInterest, edYear, edPropertyValue, edAgreementValue;
    Spinner spSalaryPer, spPropertyPer, spAgreementPer;
    TextView tvLoanBasedOnSalary, tvProperty, tvAgreement,tvMinimum, tvEmiPerLac;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_eligibility);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        linearLayout = findViewById(R.id.design_bottom_sheetcl);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        ToolIcon = findViewById(R.id.toolbar);
        calbtn = findViewById(R.id.calculate_btn);
        spAgreementPer = findViewById(R.id.eligiblity_document_sp_agreement_value_per);
        spSalaryPer = findViewById(R.id.eligible_salary_sp_emi_percentage);
        spPropertyPer = findViewById(R.id.eligiblity_property_sp_property_value_per);
        edSalary = findViewById(R.id.eligiblity_salary_ed_salary);
        edExisting = findViewById(R.id.eligiblity_salary_ed_existing_emi);
        edNewPossibleEmi = findViewById(R.id.eligiblity_salary_ed_newpossible_emi);
        edInterest = findViewById(R.id.eligiblity_salary_ed_interest_rate);
        edYear = findViewById(R.id.eligiblity_salary_ed_year);
        edPropertyValue = findViewById(R.id.eligiblity_property_ed_property_value);
        edAgreementValue = findViewById(R.id.eligiblity_document_ed_agreement_value);
        tvLoanBasedOnSalary = findViewById(R.id.activity__eligible_tv_loan_based_on_salary);
        tvProperty = findViewById(R.id.activity__eligible_tv_property);
        tvAgreement = findViewById(R.id.activity__eligible_tv_agreement);
        tvMinimum = findViewById(R.id.activity__eligible_tv_minimum);
        tvEmiPerLac = findViewById(R.id.activity__eligible_tv_emi_per_lac);

        edSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edSalary.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    edSalary.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    edSalary.setText(trim2);
                    edSalary.addTextChangedListener(this);
                    EditText editText = edSalary;
                    editText.setSelection(editText.getText().toString().trim().length());
                    int parseInt = Integer.parseInt(spSalaryPer.getSelectedItem().toString());
                    double parseDouble = (Double.parseDouble(edSalary.getText().toString().replaceAll(",", "")) * ((double) parseInt)) / 100.0d;
                    if (parseDouble < Utils.DOUBLE_EPSILON) {
                        parseDouble = 0.0d;
                    }
                    String trim3 = Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(parseDouble), "₹").replaceAll("₹", "").trim();
                    edNewPossibleEmi.setText(trim3);
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    edSalary.removeTextChangedListener(this);
                    edSalary.setText("");
                    edNewPossibleEmi.setText("");
                    edSalary.addTextChangedListener(this);
                    EditText editText2 = edSalary;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edExisting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edExisting.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    edExisting.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    edExisting.setText(trim2);
                    edExisting.addTextChangedListener(this);
                    EditText editText = edExisting;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    edExisting.removeTextChangedListener(this);
                    edExisting.setText("");
                    edExisting.addTextChangedListener(this);
                    EditText editText2 = edExisting;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edNewPossibleEmi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edNewPossibleEmi.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    edNewPossibleEmi.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    edNewPossibleEmi.setText(trim2);
                    edNewPossibleEmi.addTextChangedListener(this);
                    EditText editText = edNewPossibleEmi;
                    editText.setSelection(editText.getText().toString().trim().length());


                } else {
                    edNewPossibleEmi.removeTextChangedListener(this);
                    edNewPossibleEmi.setText("");

                    edNewPossibleEmi.addTextChangedListener(this);
                    EditText editText2 = edNewPossibleEmi;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edPropertyValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edPropertyValue.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    edPropertyValue.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    edPropertyValue.setText(trim2);
                    edPropertyValue.addTextChangedListener(this);
                    EditText editText = edPropertyValue;
                    editText.setSelection(editText.getText().toString().trim().length());


                } else {
                    edPropertyValue.removeTextChangedListener(this);
                    edPropertyValue.setText("");

                    edPropertyValue.addTextChangedListener(this);
                    EditText editText2 = edPropertyValue;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edAgreementValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edAgreementValue.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    edAgreementValue.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    edAgreementValue.setText(trim2);
                    edAgreementValue.addTextChangedListener(this);
                    EditText editText = edAgreementValue;
                    editText.setSelection(editText.getText().toString().trim().length());


                } else {
                    edAgreementValue.removeTextChangedListener(this);
                    edAgreementValue.setText("");

                    edAgreementValue.addTextChangedListener(this);
                    EditText editText2 = edAgreementValue;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        calbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
                calculate();
            }
        });


        ToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });


        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

    private void calculate()
    {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    
    {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(Check_eligibilityActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(Check_eligibilityActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(Check_eligibilityActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(Check_eligibilityActivity.this, Check_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(Check_eligibilityActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(Check_eligibilityActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(Check_eligibilityActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(Check_eligibilityActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(Check_eligibilityActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(Check_eligibilityActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_compound: {
                Intent i = new Intent(Check_eligibilityActivity.this, Compond_interestActivity.class);
                startActivity(i);
                break;
            }

        }

        return false;
    }
}