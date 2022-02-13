package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Compond_interestActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

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
    @BindView(R.id.imgShare)
    ImageView imgShare;


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

    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout linearLayout;

    InputMethodManager inputMethodManager;
    Double f3855n,f3858q,f3860s,f3859r,f3856o,f3857p;
    boolean f3852k = true;
    boolean f3851j = true;

    String f3853l = "₹";
    ImageView reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compond_interest);
        ButterKnife.bind(this);

        Toolbtn = findViewById(R.id.toolbar);
        linearLayout = findViewById(R.id.bottom_sheet_linear);
        reset = findViewById(R.id.reset);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        TenureYears_rb.setTextColor(getResources().getColor(R.color.white));


        Toolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(Compond_interestActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(Compond_interestActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(Compond_interestActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(Compond_interestActivity.this, Check_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(Compond_interestActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(Compond_interestActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(Compond_interestActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(Compond_interestActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(Compond_interestActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(Compond_interestActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_compound: {
                Intent i = new Intent(Compond_interestActivity.this, Compond_interestActivity.class);
                startActivity(i);
                break;
            }

        }

        return false;
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
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        inputMethodManager = (InputMethodManager) getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(rootview.getWindowToken(), 0);

        if (etPrincipal.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Principal", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etInterestRate.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Rate of Return", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etTenure.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Tenure", Toast.LENGTH_SHORT).show();
            return false;
        }
        Double valueOf = Double.valueOf(Double.parseDouble(etPrincipal.getText().toString().replaceAll(",", "")));
        Double valueOf2 = Double.valueOf(Double.parseDouble(etInterestRate.getText().toString()));
        Double valueOf3 = Double.valueOf(Double.parseDouble(etTenure.getText().toString()));

        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
            Snackbar.make(rootview, "Enter the value between 0.1 to 99.99", Snackbar.LENGTH_SHORT).show();
            return false;
        }else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON) {
            Snackbar.make(rootview, "Enter the value more than zero", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}