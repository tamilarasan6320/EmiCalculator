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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_Functions;
import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.R;
import com.example.emi_calculator.Utility.Utility_CalculateAmount;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.Utility.Utility_CalculateTenure;
import com.example.emi_calculator.statistics.Design_StatisticsActivity;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class Tenure_calFragment extends Fragment {

    View rootview;

    EditText ed_emi,ed_amount, ed_interest;
    Button btn_viewstatistics,btn_calculate,emi_btn_reset,btn_share;
    TextView principal_amount, interest_amount, total_payable, result_view_emi,result_view_title;
    TextView principal_amount_percentage, interest_amount_percentage;

    Utility_CalculateEMI utility_calculateEMI;
    Utility_CalculateTenure utility_calculateTenure;
    InputMethodManager inputMethodManager;


    boolean calculatestatus = false;
    String emivalue, interestamt;
    String currency = "₹";
    LinearLayout calculateemi_ll_visible;
    public Tenure_calFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_tenure_cal, container, false);

        calculateemi_ll_visible = rootview.findViewById(R.id.calculateemi_ll_visible);
        ed_emi = rootview.findViewById(R.id.activity_calculate_emi_ed_emi);
        ed_amount = rootview.findViewById(R.id.activity_calculate_emi_ed_amount);
        ed_interest = rootview.findViewById(R.id.activity_calculate_emi_ed_interest);
        btn_viewstatistics = rootview.findViewById(R.id.activity_calculate_emi_btn_viewstatistics);


        utility_calculateEMI = new Utility_CalculateEMI();
        utility_calculateTenure = new Utility_CalculateTenure();
        principal_amount = rootview.findViewById(R.id.result_view_principal_amount);
        interest_amount = rootview.findViewById(R.id.result_view_interest_amount);
        total_payable = rootview.findViewById(R.id.result_view_total_payable);
        result_view_emi = rootview.findViewById(R.id.result_view_emi);
        result_view_title = rootview.findViewById(R.id.result_view_title);

        btn_calculate = rootview.findViewById(R.id.activity_calculate_emi_btn_calculate);

        principal_amount_percentage = rootview.findViewById(R.id.result_view_principal_amount_percentage);
        interest_amount_percentage = rootview.findViewById(R.id.result_view_interest_amount_percentage);
        emi_btn_reset = rootview.findViewById(R.id.activity_calculate_emi_btn_reset);
        btn_share = rootview.findViewById(R.id.btn_share);
        utility_calculateTenure = new Utility_CalculateTenure();
        utility_calculateEMI = new Utility_CalculateEMI();

        result_view_title.setText("Tenure(in months)");
        btn_viewstatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculatestatus){
                    boolean z;
                    if (ed_amount.getText().length() <= 0 || ed_interest.getText().length() <= 0 || total_payable.getText().length() <= 0) {
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
                        intent.putExtra("tenure", ""+result_view_emi.getText().toString());
                        intent.putExtra("amount", ed_amount.getText().toString());
                        intent.putExtra("interest", ed_interest.getText().toString());
                        intent.putExtra("emi", emivalue);
                        intent.putExtra("interestAmount", interestamt);
                        intent.putExtra("Currency", currency);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(getActivity(), "Calculate Value First", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", Constant_Variable.getShareMessage(getActivity()));
                startActivity(intent);
            }
        });
        ed_emi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = ed_emi.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    ed_emi.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    ed_emi.setText(trim2);
                    ed_emi.addTextChangedListener(this);
                    EditText editText = ed_emi;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    ed_emi.removeTextChangedListener(this);
                    ed_emi.setText("");
                    ed_emi.addTextChangedListener(this);
                    EditText editText2 = ed_emi;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

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
                if (ed_amount.getText().length() > 0 && ed_emi.getText().length() > 0 && ed_interest.getText().length() > 0 && !ed_interest.getText().toString().equalsIgnoreCase(".")) {
                    calculate();
                }



            }
        });




        emi_btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_emi.setText("");
                ed_amount.setText("");
                ed_interest.setText("");
                principal_amount_percentage.setText("(00.00%)");
                interest_amount_percentage.setText("(00.00%)");
                principal_amount.setText("0");
                interest_amount.setText("0");
                total_payable.setText("₹ " + "0");
                result_view_emi.setText("₹ " + "0");
                calculateemi_ll_visible.setVisibility(View.GONE);


            }
        });
        return rootview;
    }

    private void calculate()
    {
        String rupees = "₹ ";
        Double valueOf = Double.valueOf(Double.parseDouble(ed_amount.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(ed_emi.getText().toString().replaceAll(",", "")));
        Double valueOf3 = Double.valueOf(Double.parseDouble(ed_interest.getText().toString()));
        emivalue = ed_emi.getText().toString().replaceAll(",","");
        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
        } else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() >= 100.0d) {
            Snackbar.make(rootview, "Enter the value between 0.1 to 99.99", Snackbar.LENGTH_SHORT).show();
        }else{
            calculateemi_ll_visible.setVisibility(View.VISIBLE);
            calculatestatus = true;
            String emiamount = utility_calculateEMI.getEmiamount(ed_amount.getText().toString(), "" + 999, ed_interest.getText().toString(), "0");

            Double valueOf4 = Double.valueOf(Double.parseDouble(emiamount));
            if (valueOf2.doubleValue() > valueOf4.doubleValue()){
                String str = utility_calculateTenure.gettenure(ed_amount.getText().toString(), ed_emi.getText().toString(), ed_interest.getText().toString());

                String str2 = (Math.round(Double.parseDouble(utility_calculateTenure.getTotalPayable(ed_emi.getText().toString().replaceAll(",", "")))) - Math.round(Double.parseDouble(ed_amount.getText().toString().replaceAll(",", "")))) + "";
                double doubleValue = valueOf.doubleValue() + Double.parseDouble(str2);
                interestamt = ""+Math.round(Double.parseDouble(str2));

                principal_amount_percentage.setText("(" + Constant_Functions.getPercentage(doubleValue, valueOf.doubleValue()) + ")");
                interest_amount_percentage.setText("(" + Constant_Functions.getPercentage(doubleValue, Double.parseDouble(str2.replaceAll(",", ""))) + ")");
                result_view_emi.setText(str);
                principal_amount.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(Double.parseDouble(ed_amount.getText().toString().replaceAll(",", "")))));
                interest_amount.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(Double.parseDouble(str2))));
                total_payable.setText(rupees + Constant_CurrencyFormat.rupeeFormat(""+Math.round(doubleValue)));
                SharedPreferences.Editor edit = getActivity().getSharedPreferences("ShareMessage", 0).edit();
                edit.putString("loanamount", principal_amount.getText().toString());
                edit.putString("intrestrate", interest_amount.getText().toString()+ " %");
                edit.putString("tenure", (Float.parseFloat(str) / 12.0f) + " Years (" + str + " Months)");
                edit.putString("eminAmount", ed_emi.getText().toString());
                edit.putString("totalintrestpayable", rupees+this.interest_amount.getText().toString());
                edit.putString("totalpayable", rupees+total_payable.getText().toString());
                edit.apply();


            }
            else{
                new AlertDialog.Builder(getActivity()).setTitle("Alert").setMessage("Enter your EMI more than " + valueOf4).setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();

            }








        }

    }
}