package com.example.emi_calculator.calculate_emi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_Functions;
import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.R;
import com.example.emi_calculator.Utility.Utility_CalculateAmount;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.Utility.Utility_CalculateTenure;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class Roi_calFragment extends Fragment {
    RangeSlider emiSlider, loanamtSlider, loantenureSlider;
    EditText EmiEt, LoanamtEt, LoantenureEt;
    TabLayout tabLayout;
    TextView y_m;


    BottomSheetBehavior bottomSheetBehavior;


    int loanTenureValue;
    TextView principleamt_tv, interestpay_tv, totalpayment_tv, totalamt_tv;
    Button calbtn;
    LinearLayout linearLayout;
    boolean loantenuremonth = false, loantenureyear = false;

    Utility_CalculateAmount utility_calculateAmount;
    Utility_CalculateInterestRate utility_calculateInterestRate;

    TextView principle_percentage, interest_percentage, title;

    InputMethodManager inputMethodManager;
    View rootview;
    ImageView reset,share;
    TextView Bottomheadtext;


    public Roi_calFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_roi_cal, container, false);
        utility_calculateAmount = new Utility_CalculateAmount();
        utility_calculateInterestRate = new Utility_CalculateInterestRate();

        Bottomheadtext = rootview.findViewById(R.id.bottomheadtext);


        emiSlider = rootview.findViewById(R.id.emi_rs);
        loanamtSlider = rootview.findViewById(R.id.loanamt_rs);
        loantenureSlider = rootview.findViewById(R.id.tenure_rs);

        EmiEt = rootview.findViewById(R.id.emi_et);
        LoanamtEt = rootview.findViewById(R.id.loanamt_et);
        LoantenureEt = rootview.findViewById(R.id.tenure_et);

        principleamt_tv = rootview.findViewById(R.id.p_tv);
        interestpay_tv = rootview.findViewById(R.id.i_tv);
        totalpayment_tv = rootview.findViewById(R.id.ta_tv);
        totalamt_tv = rootview.findViewById(R.id.totalhead_tv);

        linearLayout = rootview.findViewById(R.id.bottom_sheet_linear);
        y_m = rootview.findViewById(R.id.ym_et);

        calbtn = rootview.findViewById(R.id.calculate_btn);

        principle_percentage = rootview.findViewById(R.id.principle_percentage);
        interest_percentage = rootview.findViewById(R.id.interest_percentage);

        title = rootview.findViewById(R.id.title);
        reset = rootview.findViewById(R.id.reset);
        share = rootview.findViewById(R.id.share);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tab_layoutym);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);


        loantenuremonth = true;
        title.setText("Calculate Interest");
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Year"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_START);
        y_m.setText("(Month)");
        Bottomheadtext.setText("Interest Rate(%) = ");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", Constant_Variable.getShareMessage(getActivity()));
                startActivity(intent);
            }
        });

        EmiEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = EmiEt.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    EmiEt.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    EmiEt.setText(trim2);
                    EmiEt.addTextChangedListener(this);
                    EditText editText = EmiEt;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    EmiEt.removeTextChangedListener(this);
                    EmiEt.setText("");
                    EmiEt.addTextChangedListener(this);
                    EditText editText2 = EmiEt;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        LoanamtEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = LoanamtEt.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    LoanamtEt.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    LoanamtEt.setText(trim2);
                    LoanamtEt.addTextChangedListener(this);
                    EditText editText = LoanamtEt;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    LoanamtEt.removeTextChangedListener(this);
                    LoanamtEt.setText("");
                    LoanamtEt.addTextChangedListener(this);
                    EditText editText2 = LoanamtEt;
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

                inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);


                if (LoanamtEt.getText().length() > 0 && EmiEt.getText().length() > 0 && LoantenureEt.getText().length() > 0) {
                    calculate();
                }
            }
        });


        emiSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                EmiEt.setText(Math.round(value) + "");
            }
        });
        loanamtSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                LoanamtEt.setText(Math.round(value) + "");
            }
        });
        loantenureSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

                LoantenureEt.setText(Math.round(value) + "");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmiEt.setText("");
                LoantenureEt.setText("");
                LoanamtEt.setText("");
                principle_percentage.setText("(00.00%)");
                interest_percentage.setText("(00.00%)");
                principleamt_tv.setText("0");
                interestpay_tv.setText("0");
                totalpayment_tv.setText("₹ " + "0");
                totalamt_tv.setText("₹ " + "0");

                y_m.setText("(Month)");
                loantenuremonth = true;
                loantenureyear = false;
                tabLayout.getTabAt(0).select();

                emiSlider.setValues((float) 0);
                loanamtSlider.setValues((float) 0);
                loantenureSlider.setValues((float) 0);

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    y_m.setText("(Month)");
                    loantenuremonth = true;
                    loantenureyear = false;
                } else {
                    y_m.setText("(Year)");
                    loantenuremonth = false;
                    loantenureyear = true;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });






        return rootview;
    }

    private void calculate()
    {
        String str;
        String str2;
        Double valueOf = Double.valueOf(Double.parseDouble(LoanamtEt.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(EmiEt.getText().toString().replaceAll(",", "")));
        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf2.doubleValue() > Utils.DOUBLE_EPSILON) {
            try {
                loanTenureValue = Integer.parseInt(LoantenureEt.getText().toString());
                if (loantenureyear) {
                    loanTenureValue = loanTenureValue * 12;
                }
                String interestrate = utility_calculateInterestRate.getInterestrate(LoanamtEt.getText().toString(), String.valueOf(loanTenureValue), EmiEt.getText().toString());
                double parseDouble = Double.parseDouble(interestrate.replaceAll(",", ""));
                Double valueOf3 = Double.valueOf(Double.parseDouble(""+loanTenureValue));
                if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() > 600.0d) {
                    Snackbar.make(rootview, "Enter the year less than 50 and month less than 600", Snackbar.LENGTH_SHORT).show();
                } else if (parseDouble <= Utils.DOUBLE_EPSILON) {
                    Snackbar.make(rootview, "Entered values is invalid you should increase EMI or decrease Loan Amount", Snackbar.LENGTH_SHORT).show();
                } else {
                    String rupees = "₹ ";
                    totalamt_tv.setText(""+parseDouble);
                    principle_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateInterestRate.getTotalPayment().replaceAll(",", "")), valueOf.doubleValue()) + ")");
                    interest_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateInterestRate.getTotalPayment().replaceAll(",", "")), Double.parseDouble(utility_calculateInterestRate.getTotalInterestPayable().replaceAll(",", ""))) + ")");

                    principleamt_tv.setText(rupees + LoanamtEt.getText().toString());
                    interestpay_tv.setText(rupees + utility_calculateInterestRate.getTotalInterestPayable());
                    totalpayment_tv.setText(rupees + utility_calculateInterestRate.getTotalPayment());

                    SharedPreferences.Editor edit = getActivity().getSharedPreferences("ShareMessage", 0).edit();
                    edit.putString("loanamount", principleamt_tv.getText().toString());
                    edit.putString("intrestrate", totalamt_tv.getText().toString() + " %");
                    if (loantenuremonth) {
                        str2 = (Float.parseFloat(LoantenureEt.getText().toString()) / 12.0f) + " Years (" + LoantenureEt.getText().toString() + " Months)";

                    } else {
                        int parseInt = Integer.parseInt(LoantenureEt.getText().toString());
                        str2 = parseInt + " Years (" + (parseInt * 12) + " Months)";

                    }
                    edit.putString("tenure", str2);
                    edit.putString("eminAmount", this.EmiEt.getText().toString());
                    edit.putString("totalintrestpayable", interestpay_tv.getText().toString());
                    edit.putString("totalpayable", totalpayment_tv.getText().toString());
                    edit.apply();
                }

            }catch (Exception e){
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }


            }


    }
}