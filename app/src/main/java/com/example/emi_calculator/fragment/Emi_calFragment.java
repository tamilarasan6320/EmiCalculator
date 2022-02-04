package com.example.emi_calculator.fragment;

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

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_Functions;
import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.R;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class Emi_calFragment extends Fragment {

    RangeSlider loanamtSlider, interestrateSlider, loantenureSlider;
    EditText LoanamtEt, InterestrateEt, LoantenureEt;
    TabLayout tabLayout;
    TextView y_m;


    BottomSheetBehavior bottomSheetBehavior;


    int loanTenureValue;
    TextView principleamt_tv, interestpay_tv, totalpayment_tv, totalemi_tv;
    Button calculate;
    LinearLayout linearLayout;
    boolean loantenuremonth = false, loantenureyear = false;

    Utility_CalculateEMI utility_calculateEMI;

    TextView principle_percentage, interest_percentage, title;

    InputMethodManager inputMethodManager;
    View rootview;
    ImageView reset,share;


    public Emi_calFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_emi_cal, container, false);

        utility_calculateEMI = new Utility_CalculateEMI();

        loanamtSlider = rootview.findViewById(R.id.loanamt_rs);
        interestrateSlider = rootview.findViewById(R.id.interest_rs);
        loantenureSlider = rootview.findViewById(R.id.tenure_rs);

        LoanamtEt = rootview.findViewById(R.id.loanamt_et);
        InterestrateEt = rootview.findViewById(R.id.interest_et);
        LoantenureEt = rootview.findViewById(R.id.tenure_et);

        principleamt_tv = rootview.findViewById(R.id.p_tv);
        interestpay_tv = rootview.findViewById(R.id.i_tv);
        totalpayment_tv = rootview.findViewById(R.id.ta_tv);
        totalemi_tv = rootview.findViewById(R.id.totalemi_tv);

        linearLayout = rootview.findViewById(R.id.bottom_sheet_linear);
        y_m = rootview.findViewById(R.id.ym_et);

        calculate = rootview.findViewById(R.id.calculate_btn);

        principle_percentage = rootview.findViewById(R.id.principle_percentage);
        interest_percentage = rootview.findViewById(R.id.interest_percentage);

        title = rootview.findViewById(R.id.title);
        reset = rootview.findViewById(R.id.reset);
        share = rootview.findViewById(R.id.share);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tab_layoutym);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);


        loantenuremonth = true;
        title.setText("Calculate EMI");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("SHARE",Constant_Variable.getShareMessage(getActivity()));
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", Constant_Variable.getShareMessage(getActivity()));
                startActivity(intent);
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
        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);


                if (LoanamtEt.getText().length() > 0 && InterestrateEt.getText().length() > 0 && !InterestrateEt.getText().toString().equalsIgnoreCase(".") && LoantenureEt.getText().length() > 0) {
                    calculate();
                }


            }
        });


        loanamtSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                LoanamtEt.setText(Math.round(value) + "");
            }
        });
        interestrateSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                InterestrateEt.setText(Math.round(value) + "");
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
                LoanamtEt.setText("10,000");
                LoantenureEt.setText("0");
                InterestrateEt.setText("0");
                principle_percentage.setText("(00.00%)");
                interest_percentage.setText("(00.00%)");
                principleamt_tv.setText("0");
                interestpay_tv.setText(InterestrateEt.getText().toString() + "%");
                totalpayment_tv.setText("₹ " + "0");
                totalemi_tv.setText("₹ " + "0");

                y_m.setText("(Month)");
                loantenuremonth = true;
                loantenureyear = false;
                tabLayout.getTabAt(0).select();

                loanamtSlider.setValues((float) 0);
                interestrateSlider.setValues((float) 0);
                loantenureSlider.setValues((float) 0);

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Year"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_START);
        y_m.setText("(Month)");

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

    private void calculate() {
        String str;
        String str2;
        String str3;
        Double valueOf = Double.valueOf(Double.parseDouble(LoanamtEt.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(InterestrateEt.getText().toString()));


        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
            Snackbar.make(rootview, "Enter the value between 0.1 to 99.99", Snackbar.LENGTH_SHORT).show();
        } else {
            loanTenureValue = Integer.parseInt(LoantenureEt.getText().toString());
            if (loantenureyear) {
                loanTenureValue = loanTenureValue * 12;
            }

            Double valueOf3 = Double.valueOf(Double.parseDouble(LoantenureEt.getText().toString()));
            if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() > 999.0d) {
                Snackbar.make(rootview, "Enter the year less than 50 and month less than 600", Snackbar.LENGTH_SHORT).show();
                return;
            }
            String emiamount = utility_calculateEMI.getEmiamount(LoanamtEt.getText().toString().trim(), String.valueOf(loanTenureValue), InterestrateEt.getText().toString(), "0");
            String str4 = "" + utility_calculateEMI.getTotalPayable();
            String str5 = (Math.round(Double.parseDouble(str4)) - Long.parseLong(LoanamtEt.getText().toString().replaceAll(",", ""))) + "";
            principle_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateEMI.getTotalPayable()), valueOf.doubleValue()) + ")");

            interest_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateEMI.getTotalPayable()), Double.parseDouble(str5)) + ")");
            principleamt_tv.setText("₹ " + LoanamtEt.getText().toString());
            interestpay_tv.setText("₹ " +str5);
            totalemi_tv.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(emiamount).trim());
            totalpayment_tv.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(String.valueOf(Math.round(Double.parseDouble(str4)))).trim());



            SharedPreferences.Editor edit = getActivity().getSharedPreferences("ShareMessage", 0).edit();
            edit.putString("loanamount", LoanamtEt.getText().toString());
            edit.putString("intrestrate", InterestrateEt.getText().toString() + " %");

            if (loantenuremonth) {
                str3 = (Float.parseFloat(LoantenureEt.getText().toString()) / 12.0f) + " Years (" + LoantenureEt.getText().toString() + " Months)";

            } else {
                int parseInt = Integer.parseInt(LoantenureEt.getText().toString());
                str3 = parseInt + " Years (" + (parseInt * 12) + " Months)";

            }
            edit.putString("tenure", str3);
            edit.putString("eminAmount", totalpayment_tv.getText().toString());
            edit.putString("totalintrestpayable", interestpay_tv.getText().toString());
            edit.putString("totalpayable", totalpayment_tv.getText().toString());
            edit.apply();

//            this.f3332v0 = emiamount;
//            this.f3336z0 = Double.valueOf(Double.parseDouble(emiamount));
//            String str4 = "" + this.f3304B0.getTotalPayable();
//            String str5 = (Math.round(Double.parseDouble(str4)) - Long.parseLong(this.f3309Y.getText().toString().replaceAll(",", ""))) + "";
//            f3302E0 = str5;
//            String obj = this.f3309Y.getText().toString();
//            String dollerFormat = Constant_CurrencyFormatDoller.dollerFormat(Math.round(this.f3336z0.doubleValue()) + "", this.f3331u0);
//            String dollerFormat2 = Constant_CurrencyFormatDoller.dollerFormat(str5, this.f3331u0);
//            String dollerFormat3 = Constant_CurrencyFormatDoller.dollerFormat(Math.round(Double.parseDouble(str4)) + "", this.f3331u0);
//            String dollerFormat4 = Constant_CurrencyFormatDoller.dollerFormat(obj, this.f3331u0);
//            String str6 = " ";
//            if (Boolean.valueOf(this.f3335y0.CompareValues(this.f3331u0 + " " + this.f3309Y.getText().toString(), this.f3307W.getText().toString(), this.f3308X.getText().toString(), str, this.f3331u0 + " " + dollerFormat, this.f3306V, this.f3331u0)).booleanValue()) {
//                DB_History dB_History = this.f3335y0;
//                StringBuilder sb = new StringBuilder();
//                sb.append(this.f3331u0);
//                String str7 = str6;
//                sb.append(str7);
//                sb.append(this.f3309Y.getText().toString());
//                String str8 = str;
//                str2 = str7;
//                dB_History.InsertHistoryLog(sb.toString(), this.f3307W.getText().toString(), this.f3308X.getText().toString(), str8, this.f3331u0 + str7 + dollerFormat, this.f3306V, this.f3331u0);
//            } else {
//                str2 = str6;
//            }
//            this.f3310Z.setText(this.f3331u0 + str2 + dollerFormat);
//            this.f3311a0.setText(this.f3331u0 + str2 + dollerFormat4);
//            this.f3312b0.setText(this.f3331u0 + str2 + dollerFormat2);
//            this.f3313c0.setText(this.f3331u0 + str2 + dollerFormat3);
//            this.f3316f0.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(this.f3304B0.getTotalPayable()), valueOf.doubleValue()) + ")");
//            this.f3317g0.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(this.f3304B0.getTotalPayable()), Double.parseDouble(f3302E0)) + ")");
//            this.f3318h0.setText("EMI");
//            FragmentActivity activity = getActivity();
//            getActivity();
//            SharedPreferences.Editor edit = activity.getSharedPreferences("ShareMessage", 0).edit();
//            edit.putString("loanamount", this.f3311a0.getText().toString());
//            edit.putString("intrestrate", this.f3307W.getText().toString() + " %");
//            if (this.f3334x0) {
//                int parseInt = Integer.parseInt(this.f3308X.getText().toString());
//                str3 = parseInt + " Years (" + (parseInt * 12) + " Months)";
//            } else {
//                str3 = (Float.parseFloat(this.f3308X.getText().toString()) / 12.0f) + " Years (" + this.f3308X.getText().toString() + " Months)";
//            }
//            edit.putString("tenure", str3);
//            edit.putString("eminAmount", this.f3310Z.getText().toString());
//            edit.putString("totalintrestpayable", this.f3312b0.getText().toString());
//            edit.putString("totalpayable", this.f3313c0.getText().toString());
//            edit.apply();
//            this.f3327q0.setVisibility(0);
//            this.f3329s0.post(new Runnable() {
//                public void run() {
//                    Fragment_CalculateEmiActivity.this.f3329s0.fullScroll(C0852R2.attr.behavior_draggable);
//                }
//            });
        }

    }
    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }


}