package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emi_calculator.Databasehelper.DB_BTBankSegmentMultiplier;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.adapter.AdapterBTBankSegment;
import com.example.emi_calculator.adapter.AdapterBTCompany;
import com.example.emi_calculator.model.ModelBTBankSegment;
import com.example.emi_calculator.model.ModelBTCompany;
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

public class Bt_topupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    Spinner SegmentSpin;
    TextView Multipliers_tv;
    TextInputLayout TillSanctionedAmount;
    Button Save_btn;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_topup);
        // bind the view using butterknife
        ButterKnife.bind(this);
        ToolIcon = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);


        SegmentSpin = findViewById(R.id.segment_spin);
        Multipliers_tv = findViewById(R.id.tvMultipliers);

        TillSanctionedAmount = findViewById(R.id.tilSanctionedAmount);



        Save_btn = findViewById(R.id.save_btn);

        TenureYears_rb.setTextColor(getResources().getColor(R.color.white));
        ProfeesPR_rb.setTextColor(getResources().getColor(R.color.white));
        InsurancePR_rb.setTextColor(getResources().getColor(R.color.white));

        ToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(Bt_topupActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(Bt_topupActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(Bt_topupActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(Bt_topupActivity.this, Cheack_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(Bt_topupActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(Bt_topupActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(Bt_topupActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(Bt_topupActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(Bt_topupActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(Bt_topupActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }

        }

        return false;
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

}