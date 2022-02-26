package com.example.emi_calculator.calculate_emi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_Functions;
import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.R;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.statistics.Design_StatisticsActivity;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.snackbar.Snackbar;


public class Emi_calFragment extends Fragment {

    View rootview;

    EditText ed_amount, ed_interest, ed_tenure;
    RadioButton rbTenureYears,rbTenureMonth;
    Button btn_viewstatistics,btn_calculate,emi_btn_reset,btn_share;
    TextView principal_amount, interest_amount, total_payable, result_view_emi;
    TextView principal_amount_percentage, interest_amount_percentage, title;


    int loanTenureValue;
    Utility_CalculateEMI utility_calculateEMI;
    InputMethodManager inputMethodManager;


    boolean calculatestatus = false;
    String emivalue, interestamt;
    String currency = "₹";


    public Emi_calFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_emi_cal, container, false);

        ed_amount = rootview.findViewById(R.id.activity_calculate_emi_ed_amount);
        ed_interest = rootview.findViewById(R.id.activity_calculate_emi_ed_interest);
        ed_tenure = rootview.findViewById(R.id.activity_calculate_emi_ed_tenure);
        rbTenureYears = rootview.findViewById(R.id.rbTenureYears);
        rbTenureMonth = rootview.findViewById(R.id.rbTenureMonth);
        btn_viewstatistics = rootview.findViewById(R.id.activity_calculate_emi_btn_viewstatistics);


        utility_calculateEMI = new Utility_CalculateEMI();





        principal_amount = rootview.findViewById(R.id.result_view_principal_amount);
        interest_amount = rootview.findViewById(R.id.result_view_interest_amount);
        total_payable = rootview.findViewById(R.id.result_view_total_payable);
        result_view_emi = rootview.findViewById(R.id.result_view_emi);



        btn_calculate = rootview.findViewById(R.id.activity_calculate_emi_btn_calculate);

        principal_amount_percentage = rootview.findViewById(R.id.result_view_principal_amount_percentage);
        interest_amount_percentage = rootview.findViewById(R.id.result_view_interest_amount_percentage);

        //title = rootview.findViewById(R.id.title);
        emi_btn_reset = rootview.findViewById(R.id.activity_calculate_emi_btn_reset);
        btn_share = rootview.findViewById(R.id.btn_share);

        //title.setText("Calculate EMI");

        rbTenureMonth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rbTenureYears.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    rbTenureMonth.setTextColor(Color.WHITE);
                }
            }
        });
        rbTenureYears.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rbTenureMonth.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    rbTenureYears.setTextColor(Color.WHITE);
                }
            }
        });



        btn_viewstatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculatestatus) {
                    boolean z;
                    if (ed_amount.getText().length() <= 0 || ed_interest.getText().length() <= 0 || ed_tenure.getText().length() <= 0) {
                        Toast.makeText(getActivity(), "View statistics after Calculate EMI", Toast.LENGTH_SHORT).show();
                        z = false;
                    } else {
                        z = true;
                    }
                    if (Integer.parseInt(emivalue) < 1) {
                        Toast.makeText(getActivity(), "Can't Generate Schedule for This Value", Toast.LENGTH_SHORT).show();
                        z = false;
                    }
                    if (z) {
                        String str = emivalue;
                        if (str.contains(".")) {
                            String str2 = str.split("\\.")[0];
                        }
                        Intent intent = new Intent(getActivity(), Design_StatisticsActivity.class);
                        intent.putExtra("tenure", "" + loanTenureValue);
                        intent.putExtra("amount", ed_amount.getText().toString());
                        intent.putExtra("interest", ed_interest.getText().toString());
                        intent.putExtra("emi", emivalue);
                        intent.putExtra("interestAmount", interestamt);
                        intent.putExtra("Currency", currency);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getActivity(), "Calculate Value First", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_share.setOnClickListener(new View.OnClickListener() {
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


        ed_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = ed_amount.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    ed_amount.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    ed_amount.setText(trim2);
                    ed_amount.addTextChangedListener(this);
                    EditText editText = ed_amount;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    ed_amount.removeTextChangedListener(this);
                    ed_amount.setText("");
                    ed_amount.addTextChangedListener(this);
                    EditText editText2 = ed_amount;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if (validate()) {


                    if (ed_amount.getText().length() > 0 && ed_interest.getText().length() > 0 && !ed_interest.getText().toString().equalsIgnoreCase(".") && ed_tenure.getText().length() > 0) {
                        calculate();
                    }

                }


            }
        });


       


        emi_btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_amount.setText("");
                ed_tenure.setText("");
                ed_interest.setText("");
                principal_amount_percentage.setText("(00.00%)");
                interest_amount_percentage.setText("(00.00%)");
                principal_amount.setText("0");
                interest_amount.setText("0");
                total_payable.setText("₹ " + "0");
                result_view_emi.setText("₹ " + "0");





              
            }
        });




        return rootview;
    }

    private boolean validate() {
        if (TextUtils.isEmpty(ed_amount.getText().toString())) {
            Snackbar.make(rootview, "Enter the Loan Amount", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(ed_interest.getText().toString())) {
            Snackbar.make(rootview, "Enter the Interest Rate", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(ed_tenure.getText().toString())) {
            Snackbar.make(rootview, "Enter the Tenure", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void calculate() {
        String str;
        String str2;
        String str3;
        Double valueOf = Double.valueOf(Double.parseDouble(ed_amount.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(ed_interest.getText().toString()));


        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
            Snackbar.make(rootview, "Enter the value between 0.1 to 99.99", Snackbar.LENGTH_SHORT).show();
        } else {
            calculatestatus = true;
            loanTenureValue = Integer.parseInt(ed_tenure.getText().toString());
            if (rbTenureYears.isChecked()) {
                loanTenureValue = loanTenureValue * 12;
            }

            Double valueOf3 = Double.valueOf(Double.parseDouble(ed_tenure.getText().toString()));
            if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() > 999.0d) {
                Snackbar.make(rootview, "Enter the year less than 50 and month less than 600", Snackbar.LENGTH_SHORT).show();
                return;
            }
            String emiamount = utility_calculateEMI.getEmiamount(ed_amount.getText().toString().trim(), String.valueOf(loanTenureValue), ed_interest.getText().toString(), "0");
            emivalue = emiamount;
            String str4 = "" + utility_calculateEMI.getTotalPayable();
            String str5 = (Math.round(Double.parseDouble(str4)) - Long.parseLong(ed_amount.getText().toString().replaceAll(",", ""))) + "";
            interestamt = str5;
            principal_amount_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateEMI.getTotalPayable()), valueOf.doubleValue()) + ")");

            interest_amount_percentage.setText("(" + Constant_Functions.getPercentage(Double.parseDouble(utility_calculateEMI.getTotalPayable()), Double.parseDouble(str5)) + ")");
            principal_amount.setText("₹ " + ed_amount.getText().toString());
            interest_amount.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(str5).trim());
            result_view_emi.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(emiamount).trim());
            total_payable.setText("₹ " + Constant_CurrencyFormat.rupeeFormat(String.valueOf(Math.round(Double.parseDouble(str4)))).trim());


            SharedPreferences.Editor edit = getActivity().getSharedPreferences("ShareMessage", 0).edit();
            edit.putString("loanamount", ed_amount.getText().toString());
            edit.putString("intrestrate", ed_interest.getText().toString() + " %");

            if (rbTenureMonth.isChecked()) {
                str3 = (Float.parseFloat(ed_tenure.getText().toString()) / 12.0f) + " Years (" + ed_tenure.getText().toString() + " Months)";
            } else {
                int parseInt = Integer.parseInt(ed_tenure.getText().toString());
                str3 = parseInt + " Years (" + (parseInt * 12) + " Months)";
            }
            edit.putString("tenure", str3);
            edit.putString("eminAmount", total_payable.getText().toString());
            edit.putString("totalintrestpayable", interest_amount.getText().toString());
            edit.putString("totalpayable", total_payable.getText().toString());
            edit.apply();

        }

    }

    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }


}