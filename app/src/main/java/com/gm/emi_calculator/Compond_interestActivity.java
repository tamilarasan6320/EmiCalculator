package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gm.emi_calculator.Constant.Constant_CurrencyFormat;
import com.gm.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Compond_interestActivity extends AppCompatActivity {


    ImageButton Toolbtn;


    @BindView(R.id.etInterestRate)
    EditText etInterestRate;
    @BindView(R.id.etPrincipal)
    EditText etPrincipal;
    @BindView(R.id.etTenure)
    EditText etTenure;
    @BindView(R.id.rbTenureYears)
    RadioButton TenureYears_rb;
    @BindView(R.id.rbTenureMonth)
    RadioButton TenureMonth_rb;


    @BindView(R.id.spCompoundingFrequency)
    Spinner spCompoundingFrequency;
    @BindView(R.id.tilPrincipalAmount)
    TextInputLayout tilPrincipalAmount;
    @BindView(R.id.tilTenure)
    TextInputLayout tilTenure;
    @BindView(R.id.tvCompoundAmount)
    TextView tvCompoundAmount;
    @BindView(R.id.tvCompoundPR)
    TextView tvCompoundPR;
    @BindView(R.id.tvPrincipalAmount)
    TextView tvPrincipalAmount;
    @BindView(R.id.tvTotalAmount)
    TextView tvTotalAmount;
    @BindView(R.id.calculate_btn)
    Button Calbtn;
    InputMethodManager inputMethodManager;
    LinearLayout calculateemi_ll_visible;
    Double f3855n,f3858q,f3860s,f3859r,f3856o,f3857p;
    boolean f3852k = true;
    boolean f3851j = true;

    String f3853l = "₹";
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compond_interest);
        ButterKnife.bind(this);

        TenureYears_rb.setTextColor(getResources().getColor(R.color.white));

        reset = findViewById(R.id.reset_btn);
        calculateemi_ll_visible = findViewById(R.id.calculateemi_ll_visible);

        etPrincipal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etPrincipal.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etPrincipal.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etPrincipal.setText(trim2);
                    etPrincipal.addTextChangedListener(this);
                    EditText editText = etPrincipal;
                    editText.setSelection(editText.getText().toString().trim().length());
                } else {
                    etPrincipal.removeTextChangedListener(this);
                    etPrincipal.setText("");
                    etPrincipal.addTextChangedListener(this);
                    EditText editText2 = etPrincipal;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateemi_ll_visible.setVisibility(View.GONE);
                TenureYears_rb.setTextColor(getResources().getColor(R.color.white));
                TenureMonth_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                TenureYears_rb.setChecked(true);
                etInterestRate.setText("");
                etPrincipal.setText("");
                etTenure.setText("");
                tvCompoundAmount.setText("0");
                tvCompoundPR.setText("0");
                tvPrincipalAmount.setText("0");
                tvTotalAmount.setText("0");
                spCompoundingFrequency.setSelection(0);


            }
        });


    }


    @OnClick({R.id.rbTenureYears})
    public void onClickRbTenureYears(View view) {
        this.etTenure.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        this.f3851j = false;
        this.f3852k = true;
        rbchange(TenureYears_rb);

    }
    @OnClick({R.id.rbTenureMonth})
    public void onClickRbTenureMonth(View view) {
        rbchange(TenureMonth_rb);
        this.etTenure.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        this.f3851j = true;
        this.etTenure.setError((CharSequence) null);
        if (this.f3851j) {

        }
        if (this.f3852k) {
            this.f3852k = false;
        }
    }
    public void rbchange(RadioButton radioButton) {
        TenureMonth_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        TenureYears_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        radioButton.setTextColor(getResources().getColor(R.color.White));


    }
    @OnClick({R.id.calculate_btn})
    public void onCalculate(View view) {
        if (validate(view)) {
            calculate();
        }
    }

    private void calculate()
    {
        this.f3855n = Double.valueOf(Double.parseDouble(clearFormet(this.etPrincipal.getText().toString())));
        this.f3858q = Double.valueOf(Double.parseDouble(clearFormet(this.etInterestRate.getText().toString())));
        if (this.f3855n.doubleValue() <= Utils.DOUBLE_EPSILON) {
            return;
        }
        if (this.f3858q.doubleValue() <= Utils.DOUBLE_EPSILON || this.f3858q.doubleValue() >= 1000.0d) {
            Toast.makeText(this, "Interest Rate must be less than 1000", Toast.LENGTH_SHORT).show();
            return;
        }
        Double valueOf = Double.valueOf(this.f3852k ? Double.parseDouble(this.etTenure.getText().toString()) : Double.parseDouble(this.etTenure.getText().toString()) / 12.0d);
        this.f3860s = valueOf;
        if (valueOf.doubleValue() > Utils.DOUBLE_EPSILON && this.f3860s.doubleValue() <= 999.0d) {
            if (this.spCompoundingFrequency.getSelectedItemPosition() == 0) {
                this.f3859r = Double.valueOf(1.0d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 1) {
                this.f3859r = Double.valueOf(2.0d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 2) {
                this.f3859r = Double.valueOf(4.0d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 3) {
                this.f3859r = Double.valueOf(12.0d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 4) {
                this.f3859r = Double.valueOf(26.07d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 5) {
                this.f3859r = Double.valueOf(52.14d);
            } else if (this.spCompoundingFrequency.getSelectedItemPosition() == 6) {
                this.f3859r = Double.valueOf(365.0d);
            }
            calculateemi_ll_visible.setVisibility(View.VISIBLE);
            this.f3856o = Double.valueOf((this.f3855n.doubleValue() * Math.pow(((this.f3858q.doubleValue() / 100.0d) / this.f3859r.doubleValue()) + 1.0d, this.f3859r.doubleValue() * this.f3860s.doubleValue())) - this.f3855n.doubleValue());
            this.f3857p = Double.valueOf(this.f3856o.doubleValue() + this.f3855n.doubleValue());
            TextView textView = this.tvCompoundAmount;
            textView.setText(this.f3853l + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(this.f3856o), this.f3853l));
            TextView textView2 = this.tvTotalAmount;
            textView2.setText(this.f3853l + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(this.f3857p), this.f3853l));
            TextView textView3 = this.tvPrincipalAmount;
            textView3.setText(this.f3853l + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(this.f3855n), this.f3853l));
            TextView textView4 = this.tvCompoundPR;
            textView4.setText(new DecimalFormat("##.##").format((double) ((float) ((this.f3856o.doubleValue() * 100.0d) / this.f3857p.doubleValue()))) + "%");
        }
    }
    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }

    @SuppressLint("WrongConstant")
    private boolean validate(View rootview)
    {

        inputMethodManager = (InputMethodManager) getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(rootview.getWindowToken(), 0);

        if (etPrincipal.getText().toString().equals("")) {
            etPrincipal.setError("Enter Principal");
            etPrincipal.requestFocus();
            return false;
        }else if (etInterestRate.getText().toString().equals("")) {
            etInterestRate.setError("Enter Rate of Return");
            etInterestRate.requestFocus();
            return false;
        }else if (etTenure.getText().toString().equals("")) {
            etTenure.setError("Enter Tenure");
            etTenure.requestFocus();
            return false;
        }
        Double valueOf = Double.valueOf(Double.parseDouble(etPrincipal.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(etInterestRate.getText().toString()));
        Double valueOf3 = Double.valueOf(Double.parseDouble(etTenure.getText().toString()));

        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            etPrincipal.setError("Enter the value more than zero");
            etPrincipal.requestFocus();
            return false;
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
            etInterestRate.setError("Enter the value between 0.1 to 99.99");
            etInterestRate.requestFocus();
            return false;
        }else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON) {
            etTenure.setError("Enter the value more than zero");
            etTenure.requestFocus();
            return false;
        }
        return true;
    }


    public void backbtn(View view) {
        onBackPressed();
    }
}