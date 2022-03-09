package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.Databasehelper.DB_BTHistory;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.adapter.AdapterBTBankSegment;
import com.example.emi_calculator.adapter.AdapterBTCompany;
import com.example.emi_calculator.model.ModelBTBankSegment;
import com.example.emi_calculator.model.ModelBTCompany;
import com.example.emi_calculator.model.ModelBTHistory;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

public class Bt_topupActivity extends AppCompatActivity{


    Spinner SegmentSpin;
    TextView Multipliers_tv;
    TextInputLayout TillSanctionedAmount;
    Button btnSave;
    ImageButton ToolIcon;
    ArrayList<ModelBTBankSegment> f3806n = new ArrayList<>();
    ArrayList<ModelBTCompany> f3804l = new ArrayList<>();
    AdapterBTCompany f3803k;
    AdapterBTBankSegment f3805m;
    NumberFormat f3802j = new DecimalFormat("#0");
    Utility_CalculateEMI f3807o = new Utility_CalculateEMI();

    @BindView(R.id.company_spin)
    Spinner spCompany;
    @BindView(R.id.etSanctionedAmount)
    EditText SanctionedAmount_et;
    @BindView(R.id.etOutstandingAmount)
    EditText OutstandingAmount_et;
    @BindView(R.id.etEMIPaid)
    EditText EMIPaid_et;
    @BindView(R.id.etTopupAmount)
    EditText TopupAmount_et;
    @BindView(R.id.etBTAmount)
    EditText BTAmount_et;
    @BindView(R.id.etBTROI)
    EditText BTROI_et;
    @BindView(R.id.etToupROI)
    EditText ToupROI_et;
    @BindView(R.id.etTenure)
    EditText Tenure_et;
    @BindView(R.id.rbTenureYears)
    RadioButton TenureYears_rb;
    @BindView(R.id.rbTenureMonth)
    RadioButton TenureMonth_rb;
    @BindView(R.id.etBTEmi)
    EditText BTEmi_et;
    @BindView(R.id.etToupEmi)
    EditText ToupEmi_et;
    @BindView(R.id.etProcessingFees)
    EditText ProcessingFees_et;
    @BindView(R.id.etProcessingGST)
    EditText ProcessingGST_et;
    @BindView(R.id.rbProfeesPR)
    RadioButton ProfeesPR_rb;
    @BindView(R.id.rbProfeesRS)
    RadioButton ProfeesRS_rb;
    @BindView(R.id.etInsurance)
    EditText Insurance_et;
    @BindView(R.id.etInsuranceGST)
    EditText InsuranceGST_et;
    @BindView(R.id.rbInsurancePR)
    RadioButton InsurancePR_rb;
    @BindView(R.id.rbInsuranceRS)
    RadioButton InsuranceRS_rb;
    @BindView(R.id.etToupEMIInsurance)
    EditText ToupEMIInsurance_et;
    @BindView(R.id.etTotalEMI)
    EditText TotalEMI_et;
    @BindView(R.id.etCustomerName)
    EditText CustomerName_et;
    @BindView(R.id.etCustomerMobile)
    EditText CustomerMobile_et;
    @BindView(R.id.etCustomerRemarks)
    EditText CustomerRemarks_et;
    @BindView(R.id.btnHistory)
    Button btnHistory;
    ModelBTHistory f3808p = new ModelBTHistory();
    boolean f3809q = false;
    BroadcastReceiver f3810r = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra(Constant_Variable.IS_EDIT, false)) {
                f3809q = intent.getBooleanExtra(Constant_Variable.IS_EDIT, false);
                f3808p = (ModelBTHistory) intent.getSerializableExtra(Constant_Variable.BT_HISTORY);
                mo12014M();
                mo12045u();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_topup);
        // bind the view using butterknife
        ButterKnife.bind(this);
        ToolIcon = findViewById(R.id.toolbar);


        SegmentSpin = findViewById(R.id.segment_spin);
        Multipliers_tv = findViewById(R.id.tvMultipliers);

        TillSanctionedAmount = findViewById(R.id.tilSanctionedAmount);
        btnSave = findViewById(R.id.btnSave);








        TenureYears_rb.setTextColor(getResources().getColor(R.color.white));
        ProfeesPR_rb.setTextColor(getResources().getColor(R.color.white));
        InsurancePR_rb.setTextColor(getResources().getColor(R.color.white));

        SanctionedAmount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = SanctionedAmount_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    SanctionedAmount_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    SanctionedAmount_et.setText(trim2);
                    SanctionedAmount_et.addTextChangedListener(this);
                    EditText editText = SanctionedAmount_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    SanctionedAmount_et.removeTextChangedListener(this);
                    SanctionedAmount_et.setText("");
                    SanctionedAmount_et.addTextChangedListener(this);
                    EditText editText2 = SanctionedAmount_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OutstandingAmount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = OutstandingAmount_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    OutstandingAmount_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    OutstandingAmount_et.setText(trim2);
                    OutstandingAmount_et.addTextChangedListener(this);
                    EditText editText = OutstandingAmount_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    OutstandingAmount_et.removeTextChangedListener(this);
                    OutstandingAmount_et.setText("");
                    OutstandingAmount_et.addTextChangedListener(this);
                    EditText editText2 = OutstandingAmount_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        BTAmount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = BTAmount_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    BTAmount_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    BTAmount_et.setText(trim2);
                    BTAmount_et.addTextChangedListener(this);
                    EditText editText = BTAmount_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    BTAmount_et.removeTextChangedListener(this);
                    BTAmount_et.setText("");
                    BTAmount_et.addTextChangedListener(this);
                    EditText editText2 = BTAmount_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TopupAmount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = TopupAmount_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    TopupAmount_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    TopupAmount_et.setText(trim2);
                    TopupAmount_et.addTextChangedListener(this);
                    EditText editText = TopupAmount_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    TopupAmount_et.removeTextChangedListener(this);
                    TopupAmount_et.setText("");
                    TopupAmount_et.addTextChangedListener(this);
                    EditText editText2 = TopupAmount_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        BTEmi_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = BTEmi_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    BTEmi_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    BTEmi_et.setText(trim2);
                    BTEmi_et.addTextChangedListener(this);
                    EditText editText = BTEmi_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    BTEmi_et.removeTextChangedListener(this);
                    BTEmi_et.setText("");
                    BTEmi_et.addTextChangedListener(this);
                    EditText editText2 = BTEmi_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ToupEmi_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = ToupEmi_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    ToupEmi_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    ToupEmi_et.setText(trim2);
                    ToupEmi_et.addTextChangedListener(this);
                    EditText editText = ToupEmi_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    ToupEmi_et.removeTextChangedListener(this);
                    ToupEmi_et.setText("");
                    ToupEmi_et.addTextChangedListener(this);
                    EditText editText2 = ToupEmi_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ProcessingGST_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = ProcessingGST_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    ProcessingGST_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    ProcessingGST_et.setText(trim2);
                    ProcessingGST_et.addTextChangedListener(this);
                    EditText editText = ProcessingGST_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    ProcessingGST_et.removeTextChangedListener(this);
                    ProcessingGST_et.setText("");
                    ProcessingGST_et.addTextChangedListener(this);
                    EditText editText2 = ProcessingGST_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InsuranceGST_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = InsuranceGST_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    InsuranceGST_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    InsuranceGST_et.setText(trim2);
                    InsuranceGST_et.addTextChangedListener(this);
                    EditText editText = InsuranceGST_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    InsuranceGST_et.removeTextChangedListener(this);
                    InsuranceGST_et.setText("");
                    InsuranceGST_et.addTextChangedListener(this);
                    EditText editText2 = InsuranceGST_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ToupEMIInsurance_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = ToupEMIInsurance_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    ToupEMIInsurance_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    ToupEMIInsurance_et.setText(trim2);
                    ToupEMIInsurance_et.addTextChangedListener(this);
                    EditText editText = ToupEMIInsurance_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    ToupEMIInsurance_et.removeTextChangedListener(this);
                    ToupEMIInsurance_et.setText("");
                    ToupEMIInsurance_et.addTextChangedListener(this);
                    EditText editText2 = ToupEMIInsurance_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TotalEMI_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = TotalEMI_et.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    TotalEMI_et.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    TotalEMI_et.setText(trim2);
                    TotalEMI_et.addTextChangedListener(this);
                    EditText editText = TotalEMI_et;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    TotalEMI_et.removeTextChangedListener(this);
                    TotalEMI_et.setText("");
                    TotalEMI_et.addTextChangedListener(this);
                    EditText editText2 = TotalEMI_et;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
//        mo12012K();
//        mo12013L();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                long j;
                if (mo12010I()) {
                    mo12015N();
                    if (f3809q) {
                        j = DB_BTHistory.getInstance(Bt_topupActivity.this).updateHistory(f3808p);
                        f3809q = false;
                    } else {
                        j = DB_BTHistory.getInstance(Bt_topupActivity.this).insertHistory(f3808p);
                    }
                    if (j != -1) {
                        Toast.makeText(Bt_topupActivity.this, f3809q ? "Update Successfully" : "Save Successfully", Toast.LENGTH_LONG).show();
                        reset();
                        if (f3809q) {
                            f3809q = false;
                        }
                    }

                }
            }
        });




    }

    public void mo12015N() {
        this.f3808p.setCustomerName(this.CustomerName_et.getText().toString());
        this.f3808p.setCustomerMobile(this.CustomerMobile_et.getText().toString());
        this.f3808p.setCustomerReference(this.CustomerRemarks_et.getText().toString());
        this.f3808p.setBTCompanyID(this.spCompany.getSelectedItemPosition());
        this.f3808p.setBTBankSegmentID(this.SegmentSpin.getSelectedItemPosition());
        this.f3808p.setSanctionedAmount(Long.parseLong(clearFormet(this.SanctionedAmount_et.getText().toString())));
        this.f3808p.setOutstandingAmount(Long.parseLong(clearFormet(this.OutstandingAmount_et.getText().toString())));
        this.f3808p.setEMIPaid(Long.parseLong(clearFormet(this.EMIPaid_et.getText().toString())));
        this.f3808p.setMultiplier(mo12003B());
        this.f3808p.setBTLoanAmount(Long.parseLong(clearFormet(this.BTAmount_et.getText().toString())));
        this.f3808p.setBTLoanROI(Double.parseDouble(clearFormet(this.BTROI_et.getText().toString())));
        this.f3808p.setBTLoanTenure((long) mo12007F());
        this.f3808p.setTopupLoanAmount(Long.parseLong(clearFormet(this.TopupAmount_et.getText().toString())));
        this.f3808p.setTopupLoanROI(Double.parseDouble(clearFormet(this.ToupROI_et.getText().toString())));
        this.f3808p.setTopupLoanTenure((long) mo12007F());
        TenureYears_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        this.f3808p.setProcessingFee(Double.parseDouble(clearFormet(this.ProcessingFees_et.getText().toString())));
        this.f3808p.setProcessingFeeType(mo12004C());
        this.f3808p.setProcessingFeeAmount((long) ((int) mo12005D()));
        this.f3808p.setProcessingFeeAmountwithGST(Long.parseLong(clearFormet(this.ProcessingGST_et.getText().toString())));
        this.f3808p.setInsurance(Double.parseDouble(clearFormet(this.Insurance_et.getText().toString())));
        this.f3808p.setInsuranceType(mo12050z());
        this.f3808p.setInsuranceAmount((long) ((int) mo12049y()));
        this.f3808p.setInsuranceAmountwithGST(Long.parseLong(clearFormet(this.InsuranceGST_et.getText().toString())));
        this.f3808p.setTopupLoanAmountwithInsurance((long) ((int) mo12009H()));
        if (!TextUtils.isEmpty(this.BTEmi_et.getText().toString())) {
            this.f3808p.setBTEMIAmount(Long.parseLong(clearFormet(this.BTEmi_et.getText().toString())));
        } else {
            this.f3808p.setBTEMIAmount(0);
        }
        if (!TextUtils.isEmpty(this.ToupEmi_et.getText().toString())) {
            this.f3808p.setTopupEMIAmount(Long.parseLong(clearFormet(this.ToupEmi_et.getText().toString())));
        } else {
            this.f3808p.setTopupEMIAmount(0);
        }
        this.f3808p.setTopupEMIAmountwithInsurance(Long.parseLong(clearFormet(this.TotalEMI_et.getText().toString())));
    }
    public String mo12004C() {
        return getString(this.ProfeesPR_rb.isChecked() ? R.string.rb_pr : R.string.rb_rs);
    }

    private boolean mo12010I()
    {
        if (TextUtils.isEmpty(Tenure_et.getText().toString())){
            Toast.makeText(this, "Enter the Tenure", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(BTROI_et.getText().toString())){
            Toast.makeText(this, "Enter the Valid BTROI", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(BTROI_et.getText().toString())){
            Toast.makeText(this, "Enter the BT ROI", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Float.parseFloat(BTROI_et.getText().toString()) == 0){
            Toast.makeText(this, "Enter the Valid BT ROI", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(TopupAmount_et.getText().toString())){
            Toast.makeText(this, "Enter the Topup ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(EMIPaid_et.getText().toString())){
            Toast.makeText(this, "Enter Paid EMI", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if ( 0 > Integer.parseInt(EMIPaid_et.getText().toString()) && Integer.parseInt(EMIPaid_et.getText().toString()) < 500){
            Toast.makeText(this, "Enter Paid EMI Between 0 to 500", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(OutstandingAmount_et.getText().toString())){
            Toast.makeText(this, "Enter Outstanding Amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(SanctionedAmount_et.getText().toString())){
            Toast.makeText(this, "Enter Sanctioned Amoun", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Double.parseDouble(clearFormet(this.SanctionedAmount_et.getText().toString())) < Double.parseDouble(clearFormet(this.OutstandingAmount_et.getText().toString()))) {
            Toast.makeText(this, "Outstanding Amount is less then Sanctioned amount", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }
    public void mo12014M() {
        reset();
        this.btnSave.setText(this.f3809q ? "Edit" : "Save");
        this.spCompany.setSelection(mo12048x(this.f3808p.getBTCompanyID()));
        this.SegmentSpin.setSelection(mo12047w(this.f3808p.getBTBankSegmentID()));
        this.SanctionedAmount_et.setText(String.valueOf(this.f3808p.getSanctionedAmount()));
        this.OutstandingAmount_et.setText(String.valueOf(this.f3808p.getOutstandingAmount()));
        this.EMIPaid_et.setText(String.valueOf(this.f3808p.getEMIPaid()));
        this.Multipliers_tv.setText(String.valueOf(this.f3808p.getMultiplier()));
        this.BTAmount_et.setText(String.valueOf(this.f3808p.getBTLoanAmount()));
        this.TopupAmount_et.setText(String.valueOf(this.f3808p.getTopupLoanAmount()));
        this.BTROI_et.setText(String.valueOf(this.f3808p.getBTLoanROI()));
        this.ToupROI_et.setText(String.valueOf(this.f3808p.getTopupLoanROI()));
        this.Tenure_et.setText(String.valueOf(this.f3808p.getBTLoanTenure()));
        this.TenureMonth_rb.setChecked(true);
        mo12018Q(this.TenureMonth_rb);
        this.TenureYears_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        this.BTEmi_et.setText(String.valueOf(this.f3808p.getBTEMIAmount()));
        this.ToupEmi_et.setText(String.valueOf(this.f3808p.getTopupEMIAmount()));
        this.ProcessingFees_et.setText(String.valueOf(this.f3808p.getProcessingFee()));
        mo12017P(String.valueOf(this.f3808p.getProcessingFeeType()));
        this.ProcessingGST_et.setText(String.valueOf(this.f3808p.getProcessingFeeAmountwithGST()));
        this.Insurance_et.setText(String.valueOf(this.f3808p.getInsurance()));
        mo12016O(String.valueOf(this.f3808p.getInsuranceType()));
        this.InsuranceGST_et.setText(String.valueOf(this.f3808p.getInsuranceAmountwithGST()));
        this.ToupEMIInsurance_et.setText(String.valueOf(this.f3808p.getTopupEMIAmountwithInsurance()));
        this.TotalEMI_et.setText(String.valueOf(this.f3808p.getTopupEMIAmountwithInsurance()));
        this.CustomerName_et.setText(this.f3808p.getCustomerName());
        this.CustomerMobile_et.setText(this.f3808p.getCustomerMobile());
        this.CustomerRemarks_et.setText(this.f3808p.getCustomerReference());
    }
    public void reset() {
        this.spCompany.setSelection(0);
        this.SegmentSpin.setSelection(0);
        this.SanctionedAmount_et.setText((CharSequence) null);
        this.OutstandingAmount_et.setText((CharSequence) null);
        this.EMIPaid_et.setText((CharSequence) null);
        this.Multipliers_tv.setText((CharSequence) null);
        this.BTAmount_et.setText((CharSequence) null);
        this.TopupAmount_et.setText((CharSequence) null);
        this.BTROI_et.setText((CharSequence) null);
        this.ToupROI_et.setText((CharSequence) null);
        this.Tenure_et.setText((CharSequence) null);
        this.TenureYears_rb.callOnClick();
        this.TenureYears_rb.setChecked(true);
        mo12020S(this.TenureYears_rb);
        this.BTEmi_et.setText((CharSequence) null);
        this.ToupEmi_et.setText((CharSequence) null);
        this.ProcessingFees_et.setText("0");
        this.ProfeesRS_rb.callOnClick();
        this.ProfeesRS_rb.setChecked(true);
        mo12019R(this.ProfeesRS_rb);
        this.ProcessingGST_et.setText("0");
        this.Insurance_et.setText("0");
        this.InsuranceRS_rb.callOnClick();
        this.InsuranceRS_rb.setChecked(true);
        mo12018Q(this.InsuranceRS_rb);
        this.InsuranceGST_et.setText("0");
        this.ToupEMIInsurance_et.setText((CharSequence) null);
        this.TotalEMI_et.setText((CharSequence) null);
        this.CustomerName_et.setText((CharSequence) null);
        this.CustomerMobile_et.setText((CharSequence) null);
        this.CustomerRemarks_et.setText((CharSequence) null);
        this.btnSave.setText(this.f3809q ? "Edit" : "Save");
        this.SanctionedAmount_et.setError((CharSequence) null);
        this.OutstandingAmount_et.setError((CharSequence) null);
        this.EMIPaid_et.setError((CharSequence) null);
        this.Multipliers_tv.setError((CharSequence) null);
        this.BTAmount_et.setError((CharSequence) null);
        this.TopupAmount_et.setError((CharSequence) null);
        this.BTROI_et.setError((CharSequence) null);
        this.ToupROI_et.setError((CharSequence) null);
        this.Tenure_et.setError((CharSequence) null);
        this.BTEmi_et.setError((CharSequence) null);
        this.ToupEmi_et.setError((CharSequence) null);
        this.ToupEMIInsurance_et.setError((CharSequence) null);
        this.TotalEMI_et.setError((CharSequence) null);
        this.CustomerName_et.setError((CharSequence) null);
        this.CustomerMobile_et.setError((CharSequence) null);
        this.CustomerRemarks_et.setError((CharSequence) null);
    }
    public void mo12017P(String str) {
        if (str.equalsIgnoreCase(getString(R.string.rb_pr))) {
            this.ProfeesPR_rb.setChecked(true);
            mo12019R(this.ProfeesPR_rb);
            return;
        }
        this.ProfeesRS_rb.setChecked(true);
        mo12019R(this.ProfeesRS_rb);
    }
    public void mo12016O(String str) {
        if (str.equalsIgnoreCase(getString(R.string.rb_pr))) {
            this.InsurancePR_rb.setChecked(true);
            mo12018Q(this.InsurancePR_rb);
            return;
        }
        this.InsuranceRS_rb.setChecked(true);
        mo12018Q(this.InsuranceRS_rb);
    }
    public int mo12048x(int i) {
        for (int i2 = 0; i2 < this.f3804l.size(); i2++) {
            if (this.f3804l.get(i2).getBTCompanyID() == i) {
                return i2;
            }
        }
        return 0;
    }
    public int mo12047w(int i) {
        for (int i2 = 0; i2 < this.f3806n.size(); i2++) {
            if (this.f3806n.get(i2).getBTBankSegmentID() == i) {
                return i2;
            }
        }
        return 0;
    }

    private void mo12045u() {
        Multipliers_tv.setText(String.valueOf(mo12003B()));
        mo12021T();
        mo12043s();
        mo12006E();
        mo12002A();
        mo12044t();
        mo12008G();
        TotalEMI_et.setText(String.valueOf(this.f3802j.format(mo12043s() + mo12008G())));
    }
    public double mo12008G() {
        if (TextUtils.isEmpty(InsuranceGST_et.getText()) || TextUtils.isEmpty(TopupAmount_et.getText()) || TextUtils.isEmpty(ToupROI_et.getText()) || TextUtils.isEmpty(Tenure_et.getText()) || ToupROI_et.getText().toString().equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = Double.parseDouble(this.f3807o.getEmiamount(String.valueOf(mo12009H()), String.valueOf(TenureMonth_rb.isChecked() ? Integer.parseInt(Tenure_et.getText().toString()) : Integer.parseInt(Tenure_et.getText().toString()) * 12), ToupROI_et.getText().toString(), "0"));
        this.ToupEMIInsurance_et.setText(String.valueOf(this.f3802j.format(parseDouble)));
        return parseDouble;
    }
    public double mo12009H() {
        return Double.parseDouble(TopupAmount_et.getText().toString().replaceAll(",", "")) + Double.parseDouble(InsuranceGST_et.getText().toString().replaceAll(",", ""));
    }



    public double mo12044t() {
        if (TextUtils.isEmpty(this.TopupAmount_et.getText()) || TextUtils.isEmpty(this.ToupROI_et.getText()) || TextUtils.isEmpty(Tenure_et.getText()) || ToupROI_et.getText().toString().equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = Double.parseDouble(this.f3807o.getEmiamount(TopupAmount_et.getText().toString(), String.valueOf(mo12007F()), ToupROI_et.getText().toString(), "0"));
        ToupEmi_et.setText(String.valueOf(this.f3802j.format(parseDouble)));
        return parseDouble;
    }
    public double mo12002A() {
        double y = mo12049y() + ((mo12049y() * 18.0d) / 100.0d);
        this.InsuranceGST_et.setText(String.valueOf(this.f3802j.format(y)));
        return y;
    }
    public double mo12043s() {
        if (TextUtils.isEmpty(BTAmount_et.getText()) || TextUtils.isEmpty(BTROI_et.getText()) || TextUtils.isEmpty(Tenure_et.getText()) || BTROI_et.getText().toString().equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = Double.parseDouble(this.f3807o.getEmiamount(BTAmount_et.getText().toString(), String.valueOf(mo12007F()), BTROI_et.getText().toString(), "0"));
        BTEmi_et.setText(String.valueOf(this.f3802j.format(parseDouble)));
        return parseDouble;
    }
    public double mo12006E() {
        double D = mo12005D() + ((mo12005D() * 18.0d) / 100.0d);
        this.ProcessingGST_et.setText(String.valueOf(this.f3802j.format(D)));
        return D;
    }
    public double mo12005D() {
        if (TextUtils.isEmpty(BTAmount_et.getText()) || TextUtils.isEmpty(TopupAmount_et.getText()) || TextUtils.isEmpty(ProcessingFees_et.getText()) || ProcessingFees_et.getText().toString().equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = Double.parseDouble(clearFormet(BTAmount_et.getText().toString())) + Double.parseDouble(clearFormet(TopupAmount_et.getText().toString()));
        if (this.ProfeesPR_rb.isChecked()) {
            return (parseDouble * Double.parseDouble(this.ProcessingFees_et.getText().toString().replaceAll(",", ""))) / 100.0d;
        }
        return Double.parseDouble(this.ProcessingFees_et.getText().toString().replaceAll(",", ""));
    }
    public int mo12007F() {
        return this.TenureMonth_rb.isChecked() ? Integer.parseInt(Tenure_et.getText().toString()) : Integer.parseInt(Tenure_et.getText().toString()) * 12;
    }
    public double mo12003B() {
        return 1;
        //return (TextUtils.isEmpty(EMIPaid_et.getText())) ? Utils.DOUBLE_EPSILON : DB_BTBankSegmentMultiplier.getInstance(this).getBankSegmentMultiplier(this.f3806n.get(this.SegmentSpin.getSelectedItemPosition()).getBTBankSegmentID(), Integer.parseInt(this.EMIPaid_et.getText().toString()));
    }
    public double mo12021T() {
        if (TextUtils.isEmpty(SanctionedAmount_et.getText()) || TextUtils.isEmpty(EMIPaid_et.getText()) || TextUtils.isEmpty(OutstandingAmount_et.getText())) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = (Double.parseDouble(clearFormet(SanctionedAmount_et.getText().toString())) * mo12003B()) - Double.parseDouble(clearFormet(OutstandingAmount_et.getText().toString()));
        TopupAmount_et.setText(String.valueOf(this.f3802j.format(parseDouble)));
        return parseDouble;
    }
    public double mo12049y() {
        if (TextUtils.isEmpty(this.BTAmount_et.getText()) || TextUtils.isEmpty(this.TopupAmount_et.getText()) || TextUtils.isEmpty(this.Insurance_et.getText()) || this.Insurance_et.getText().toString().equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        double parseDouble = Double.parseDouble(clearFormet(this.BTAmount_et.getText().toString())) + Double.parseDouble(clearFormet(this.TopupAmount_et.getText().toString()));
        if (this.InsurancePR_rb.isChecked()) {
            return (parseDouble * Double.parseDouble(this.Insurance_et.getText().toString().replaceAll(",", ""))) / 100.0d;
        }
        return Double.parseDouble(this.Insurance_et.getText().toString().replaceAll(",", ""));
    }
    @OnClick({R.id.btnHistory})
    public void onClickBtnHistory() {
        startActivity(new Intent(this, BTHistoryActivity.class));
    }


    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etInsurance})
    public void onInsuranceChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnClick({R.id.rbProfeesPR})
    public void onClickRbProfeesPR(View view) {
        mo12019R(ProfeesPR_rb);
    }
    @OnClick({R.id.rbProfeesRS})
    public void onClickRbProfeesRS(View view) {
        mo12019R(ProfeesRS_rb);
    }
    @OnClick({R.id.rbInsuranceRS})
    public void onClickrbRbInsuranceRS(View view) {
        mo12018Q(InsuranceRS_rb);
    }

    public void mo12019R(RadioButton radioButton) {
        ProfeesPR_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        ProfeesRS_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        radioButton.setTextColor(getResources().getColor(R.color.white));
        mo12045u();
    }

    public void mo12011J() {
        if (!TextUtils.isEmpty(this.SanctionedAmount_et.getText()) && !TextUtils.isEmpty(this.OutstandingAmount_et.getText())) {
            if (Double.parseDouble(clearFormet(this.SanctionedAmount_et.getText().toString())) < Double.parseDouble(clearFormet(this.OutstandingAmount_et.getText().toString()))) {
                this.OutstandingAmount_et.setError("Outstanding Amount is less then Sanctioned amount");
            } else {
                this.OutstandingAmount_et.setError((CharSequence) null);
            }
        }
    }
    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etProcessingFees})
    public void onProcessingFeesChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnClick({R.id.rbTenureYears})
    public void onClickRbTenureYears(View view) {
        mo12020S(TenureYears_rb);
    }
    @OnClick({R.id.rbInsurancePR})
    public void onClickRbInsurancePR(View view) {
        mo12018Q(InsurancePR_rb);
    }

    public void mo12018Q(RadioButton radioButton) {
        InsurancePR_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        InsuranceRS_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        radioButton.setTextColor(getResources().getColor(R.color.white));
        mo12045u();
    }

    public void mo12020S(RadioButton radioButton) {
        TenureMonth_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        TenureYears_rb.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        radioButton.setTextColor(getResources().getColor(R.color.White));
        mo12045u();
    }
    @OnClick({R.id.rbTenureMonth})
    public void onClickRbTenureMonth(View view) {
        mo12020S(TenureMonth_rb);
    }

    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etTenure})
    public void onTenureChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etBTROI})
    public void onBTROIChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etBTAmount})
    public void onBTAmountChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnItemSelected(R.id.company_spin)
    public void onItemSelectedSpCompany(Spinner spinner, int i) {
        //this.f3806n = DB_BTBankSegment.getInstance(this).getAllBankSegmentByCompanyID(this.f3804l.get(i).getBTCompanyID());
        //mo12013L();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etSanctionedAmount})
    public void onSanctionedAmountChange(CharSequence charSequence) {
//        if (charSequence.toString().length() <= 0) {
//            this.tilSanctionedAmount.setHint((CharSequence) getString(C0845R.string.hint_sanctioned_amount));
//        } else {
//            TextInputLayout textInputLayout = this.tilSanctionedAmount;
//            textInputLayout.setHint((CharSequence) getString(C0845R.string.hint_sanctioned_amount) + " (" + Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(charSequence.toString().replaceAll(",", ""))).trim() + ")");
//        }
        mo12011J();
        mo12045u();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etOutstandingAmount})
    public void onOutstandingAmountChange(CharSequence charSequence) {
        BTAmount_et.setText(charSequence.toString());
//        if (charSequence.toString().length() <= 0) {
//            this.tilOutstandingAmount.setHint((CharSequence) getString(C0845R.string.hint_outstanding_amount));
//        } else {
//            TextInputLayout textInputLayout = this.tilOutstandingAmount;
//            textInputLayout.setHint((CharSequence) getString(C0845R.string.hint_outstanding_amount) + " (" + Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(charSequence.toString().replaceAll(",", ""))).trim() + ")");
//        }
        mo12011J();
        mo12045u();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etEMIPaid})
    public void onEMIPaidChange(CharSequence charSequence) {
        mo12045u();
    }
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {R.id.etTopupAmount})
    public void onToupAmountChange(CharSequence charSequence) {
    }
    public void mo12012K() {
        AdapterBTCompany adapterBTCompany = new AdapterBTCompany(this, this.f3804l);
        this.f3803k = adapterBTCompany;
        this.spCompany.setAdapter(adapterBTCompany);
    }
    public void mo12013L() {
        AdapterBTBankSegment adapterBTBankSegment = new AdapterBTBankSegment(this, this.f3806n);
        this.f3805m = adapterBTBankSegment;
        this.SegmentSpin.setAdapter(adapterBTBankSegment);
    }
    public String mo12050z() {
        return getString(this.InsurancePR_rb.isChecked() ? R.string.rb_pr : R.string.rb_rs);
    }

}