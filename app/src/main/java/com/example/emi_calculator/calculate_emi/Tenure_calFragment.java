package com.example.emi_calculator.calculate_emi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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


public class Tenure_calFragment extends Fragment {

    RangeSlider emiSlider, interestrateSlider, loanamtSlider;
    EditText LoanamtEt, EmiEt, InterestrateEt;

    BottomSheetBehavior bottomSheetBehavior;
    TextView principleamt_tv, interestpay_tv, totalpayment_tv, totalamt_tv;
    Button calbtn;
    LinearLayout linearLayout;
    boolean loantenuremonth = false, loantenureyear = false;

    Utility_CalculateTenure utility_calculateTenure;
    Utility_CalculateEMI utility_calculateEMI;

    TextView principle_percentage, interest_percentage, title;

    InputMethodManager inputMethodManager;
    View rootview;
    ImageView reset,share;

    TextView Bottomheadtext;
    public Tenure_calFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_tenure_cal, container, false);
        utility_calculateTenure = new Utility_CalculateTenure();
        utility_calculateEMI = new Utility_CalculateEMI();

        Bottomheadtext = rootview.findViewById(R.id.bottomheadtext);


        emiSlider = rootview.findViewById(R.id.emi_rs);
        interestrateSlider = rootview.findViewById(R.id.interest_rs);
        loanamtSlider = rootview.findViewById(R.id.loanamt_rs);

        EmiEt = rootview.findViewById(R.id.emi_et);
        InterestrateEt = rootview.findViewById(R.id.interest_et);
        LoanamtEt = rootview.findViewById(R.id.loanamt_et);

        principleamt_tv = rootview.findViewById(R.id.p_tv);
        interestpay_tv = rootview.findViewById(R.id.i_tv);
        totalpayment_tv = rootview.findViewById(R.id.ta_tv);
        totalamt_tv = rootview.findViewById(R.id.totalhead_tv);

        linearLayout = rootview.findViewById(R.id.bottom_sheet_linear);

        calbtn = rootview.findViewById(R.id.calculate_btn);

        principle_percentage = rootview.findViewById(R.id.principle_percentage);
        interest_percentage = rootview.findViewById(R.id.interest_percentage);

        title = rootview.findViewById(R.id.title);
        reset = rootview.findViewById(R.id.reset);
        share = rootview.findViewById(R.id.share);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);


        loantenuremonth = true;
        title.setText("Calculate Loan Tenure");


        Bottomheadtext.setText("Tenure(in Months) = ");

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
                if (LoanamtEt.getText().length() > 0 && EmiEt.getText().length() > 0 && InterestrateEt.getText().length() > 0 && !InterestrateEt.getText().toString().equalsIgnoreCase(".")) {
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
        interestrateSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                InterestrateEt.setText(Math.round(value) + "");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmiEt.setText("");
                LoanamtEt.setText("");
                InterestrateEt.setText("");
                principle_percentage.setText("(00.00%)");
                interest_percentage.setText("(00.00%)");
                principleamt_tv.setText("0");
                interestpay_tv.setText("0");
                totalpayment_tv.setText("₹ " + "0");
                totalamt_tv.setText("₹ " + "0");


                loantenuremonth = true;
                loantenureyear = false;

                emiSlider.setValues((float) 0);
                interestrateSlider.setValues((float) 0);
                loanamtSlider.setValues((float) 0);

            }
        });
        return rootview;
    }

    private void calculate()
    {
        String rupees = "₹ ";
        Double valueOf = Double.valueOf(Double.parseDouble(LoanamtEt.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(EmiEt.getText().toString().replaceAll(",", "")));
        Double valueOf3 = Double.valueOf(Double.parseDouble(InterestrateEt.getText().toString()));
        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() >= 100.0d) {
            Snackbar.make(rootview, "Enter the value between 0.1 to 99.99", Snackbar.LENGTH_SHORT).show();
        }else{
            String emiamount = utility_calculateEMI.getEmiamount(LoanamtEt.getText().toString(), "" + 999, InterestrateEt.getText().toString(), "0");

            Double valueOf4 = Double.valueOf(Double.parseDouble(emiamount));
            if (valueOf2.doubleValue() > valueOf4.doubleValue()){
                String str = utility_calculateTenure.gettenure(LoanamtEt.getText().toString(), EmiEt.getText().toString(), InterestrateEt.getText().toString());

                String str2 = (Math.round(Double.parseDouble(utility_calculateTenure.getTotalPayable(EmiEt.getText().toString().replaceAll(",", "")))) - Math.round(Double.parseDouble(LoanamtEt.getText().toString().replaceAll(",", "")))) + "";
                double doubleValue = valueOf.doubleValue() + Double.parseDouble(str2);

                principle_percentage.setText("(" + Constant_Functions.getPercentage(doubleValue, valueOf.doubleValue()) + ")");
                interest_percentage.setText("(" + Constant_Functions.getPercentage(doubleValue, Double.parseDouble(str2.replaceAll(",", ""))) + ")");
                totalamt_tv.setText(str);
                principleamt_tv.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(Double.parseDouble(LoanamtEt.getText().toString().replaceAll(",", "")))));
                interestpay_tv.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(Double.parseDouble(str2))));
                totalpayment_tv.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(doubleValue)));
                SharedPreferences.Editor edit = getActivity().getSharedPreferences("ShareMessage", 0).edit();
                edit.putString("loanamount", principleamt_tv.getText().toString());
                edit.putString("intrestrate", InterestrateEt.getText().toString()+ " %");
                edit.putString("tenure", (Float.parseFloat(str) / 12.0f) + " Years (" + str + " Months)");
                edit.putString("eminAmount", EmiEt.getText().toString());
                edit.putString("totalintrestpayable", rupees+this.interestpay_tv.getText().toString());
                edit.putString("totalpayable", rupees+totalpayment_tv.getText().toString());
                edit.apply();


            }
            else{
                new AlertDialog.Builder(getActivity()).setTitle("Alert").setMessage("Enter your EMI more than " + valueOf4).setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();

            }








        }

    }
}