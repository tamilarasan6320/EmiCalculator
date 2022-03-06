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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.example.emi_calculator.Utility.Utility_CalculateAmount;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class Check_eligibilityActivity extends AppCompatActivity {


   // private BottomSheetBehavior bottomSheetBehavior;
    ImageButton ToolIcon;
    Button calbtn;
    LinearLayout calculateemi_ll_visible;
    LinearLayout linearLayout;
    EditText edSalary, edExisting
            , edNewPossibleEmi, edInterest, edYear, edPropertyValue, edAgreementValue;
    Spinner spSalaryPer, spPropertyPer, spAgreementPer;
    TextView tvLoanBasedOnSalary, tvProperty, tvAgreement,tvMinimum, tvEmiPerLac,tvMiniEMI;
    Utility_CalculateAmount utility_calculateAmount;
    Utility_CalculateEMI utility_calculateEMI;
    int loanTenureValue;
    Button reset_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_eligibility);

        utility_calculateEMI = new Utility_CalculateEMI();
        utility_calculateAmount = new Utility_CalculateAmount();

        reset_btn = findViewById(R.id.reset_btn);
        linearLayout = findViewById(R.id.design_bottom_sheetcl);
        calculateemi_ll_visible = findViewById(R.id.calculateemi_ll_visible);
       // bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
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
        tvMiniEMI = findViewById(R.id.activity__eligible_tv_emi);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edSalary.getText().clear();
                spSalaryPer.setSelection(0);
                edExisting.getText().clear();
                edNewPossibleEmi.getText().clear();
                edInterest.getText().clear();
                edYear.getText().clear();
                edPropertyValue.getText().clear();
                spPropertyPer.setSelection(0);
                edAgreementValue.getText().clear();
                spAgreementPer.setSelection(0);
                calculateemi_ll_visible.setVisibility(View.GONE);
            }
        });

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

                    int parseInt = Integer.parseInt(spSalaryPer.getSelectedItem().toString());
                    double parseDouble1 = (Double.parseDouble(edSalary.getText().toString().replaceAll(",", "")) * ((double) parseInt)) / 100.0d;
                    if (parseDouble1 < Utils.DOUBLE_EPSILON) {
                        parseDouble1 = 0.0d;
                    }
                    String trim4 = Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(parseDouble1), "₹").replaceAll("₹", "").trim();



                    double parseDouble = (Double.parseDouble(trim4.replaceAll(",", ""))) - (Double.parseDouble(edExisting.getText().toString().replaceAll(",", "")));
                    if (parseDouble < Utils.DOUBLE_EPSILON) {
                        parseDouble = 0.0d;
                    }
                    String trim3 = Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(parseDouble), "₹").replaceAll("₹", "").trim();
                    edNewPossibleEmi.setText(trim3);
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
        spSalaryPer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String replaceAll = edSalary.getText().toString().replaceAll(",", "");
                int parseInt = Integer.parseInt(spSalaryPer.getSelectedItem().toString());
                if (replaceAll.length() > 0) {
                    double parseDouble = (Double.parseDouble(replaceAll) * ((double) parseInt)) / 100.0d;
                    if (parseDouble < Utils.DOUBLE_EPSILON) {
                        parseDouble = 0.0d;
                    }
                    String dollerFormat = Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(parseDouble), "₹");
                    edNewPossibleEmi.setText(dollerFormat);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
                calculate();
            }
        });


        ToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });




    }

    private void calculate()
    {

        if(TextUtils.isEmpty(edSalary.getText().toString())){
            Toast.makeText(this, "Enter Month Salary", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edExisting.getText().toString()))
        {
            Toast.makeText(this, "Enter Existing EMI ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edNewPossibleEmi.getText().toString()))
        {
            Toast.makeText(this, "Enter Existing EMI ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edInterest.getText().toString()))
        {
            Toast.makeText(this, "Enter Interest ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edYear.getText().toString()))
        {
            Toast.makeText(this, "Enter Tenure in Years ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edPropertyValue.getText().toString()))
        {
            Toast.makeText(this, "Enter Prperty Value ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(edAgreementValue.getText().toString()))
        {
            Toast.makeText(this, "Enter Agreement Value ", Toast.LENGTH_SHORT).show();

        }
        else {
            Double valueOf = Double.valueOf(Double.parseDouble(edSalary.getText().toString().replaceAll(",", "")));
            Double valueOf2 = Double.valueOf(Double.parseDouble(edInterest.getText().toString()));
            Double valueOf3 = Double.valueOf(Double.parseDouble(edYear.getText().toString()));

            if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
                Toast.makeText(this, "Enter the Monthly Salary more than zero", Toast.LENGTH_SHORT).show();
            } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
                Toast.makeText(this, "Enter the value between 0.1 to 99.99", Toast.LENGTH_SHORT).show();
            }
            else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON) {
                Toast.makeText(this, "Enter the Tenure Year more than zero", Toast.LENGTH_SHORT).show();
            }
            else{
                calculateemi_ll_visible.setVisibility(View.VISIBLE);
                loanTenureValue = Integer.parseInt(edYear.getText().toString());
                loanTenureValue = loanTenureValue * 12;
                String emiamount = utility_calculateEMI.getEmiamount("100000", String.valueOf(loanTenureValue), edInterest.getText().toString(), "0");
                double propertyvalue = getPercentageValue(edPropertyValue.getText().toString().replaceAll(",", ""),spPropertyPer.getSelectedItem().toString());
                double agreementvalue = getPercentageValue(edAgreementValue.getText().toString().replaceAll(",", ""),spAgreementPer.getSelectedItem().toString());

                double loanbasedsalary = utility_calculateAmount.getamount(edNewPossibleEmi.getText().toString(), String.valueOf(loanTenureValue), edInterest.getText().toString());
                double minieligible;
                if (loanbasedsalary < propertyvalue && loanbasedsalary < agreementvalue){
                    minieligible = loanbasedsalary;

                }
                else if (propertyvalue < loanbasedsalary && propertyvalue < agreementvalue){
                    minieligible = propertyvalue;

                }
                else{
                    minieligible = agreementvalue;

                }
                String miniemiamount = utility_calculateEMI.getEmiamount(""+minieligible, String.valueOf(loanTenureValue), edInterest.getText().toString(), "0");





                tvProperty.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(""+Math.round(propertyvalue)).trim());
                tvAgreement.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(""+Math.round(agreementvalue)).trim());

                tvEmiPerLac.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(emiamount).trim());
                tvLoanBasedOnSalary.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(""+Math.round(loanbasedsalary)).trim());
                tvMinimum.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(""+Math.round(minieligible)).trim());
                tvMiniEMI.setText("EMI (₹ " + Constant_CurrencyFormat.rupeeFormat(miniemiamount).trim() + ")");


            }

        }



    }

    private double getPercentageValue(String totalvalue, String totalpercentage) {
        double percentvalue = Double.parseDouble(totalpercentage) / 100.0d;
        double result = Double.parseDouble(totalvalue) * percentvalue;


        return result;
    }


}