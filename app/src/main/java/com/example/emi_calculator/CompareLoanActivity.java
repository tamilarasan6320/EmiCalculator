package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.example.emi_calculator.Utility.Utility_CalculateLoanCompare;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CompareLoanActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private BottomSheetBehavior bottomSheetBehavior;
    EditText AmountEt1,AmountEt2,InterestEt1,InterestEt2,TenureEt1,TenureEt2;
    Spinner TypeSpin1,TypeSpin2;
    TextView Emi1tv,Emi2tv,EmiDiff,Interesttv1,Interesttv2,InterestDiff,TotalRepay1,TotalRepay2,TotalRepayDiff;
    Button Calculatebtn;
    LinearLayout linearLayout;
    ImageButton Toolbtn;
    Utility_CalculateLoanCompare utility_calculateLoanCompare;
    String rupees = "₹ ";
    String rs = "₹";
    Button Share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_loan);

        utility_calculateLoanCompare = new Utility_CalculateLoanCompare();
        Share = findViewById(R.id.share);



        Calculatebtn = findViewById(R.id.calculate_btn);
        linearLayout = findViewById(R.id.design_bottom_sheethl);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        Toolbtn = findViewById(R.id.toolbar);

        AmountEt1 = findViewById(R.id.amtEt1);
        AmountEt2 = findViewById(R.id.amtEt2);
        InterestEt1 = findViewById(R.id.interestEt1);
        InterestEt2 = findViewById(R.id.interestEt2);
        TenureEt1 = findViewById(R.id.tenureEt1);
        TenureEt2 = findViewById(R.id.tenureEt2);
        TypeSpin1 = findViewById(R.id.typespin1);
        TypeSpin2 = findViewById(R.id.typespin2);

        Emi1tv = findViewById(R.id.emi1_tv);
        Emi2tv = findViewById(R.id.emi2_tv);
        EmiDiff = findViewById(R.id.emidiff_tv);
        Interesttv1 = findViewById(R.id.interest1_tv);
        Interesttv2 = findViewById(R.id.interest2_tv);
        InterestDiff = findViewById(R.id.interestdiff_tv);
        TotalRepay1 = findViewById(R.id.total_repay1_tv);
        TotalRepay2 = findViewById(R.id.total_repay2_tv);
        TotalRepayDiff = findViewById(R.id.total_repaydiff_tv);

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView = CompareLoanActivity.this.getWindow().getDecorView().getRootView();
                rootView.setDrawingCacheEnabled(true);
                Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                rootView.setDrawingCacheEnabled(false);
                shareImage(createBitmap, CompareLoanActivity.this);
            }
        });

        AmountEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = AmountEt1.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    AmountEt1.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    AmountEt1.setText(trim2);
                    AmountEt1.addTextChangedListener(this);
                    EditText editText = AmountEt1;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    AmountEt1.removeTextChangedListener(this);
                    AmountEt1.setText("");
                    AmountEt1.addTextChangedListener(this);
                    EditText editText2 = AmountEt1;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        AmountEt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = AmountEt2.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    AmountEt2.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    AmountEt2.setText(trim2);
                    AmountEt2.addTextChangedListener(this);
                    EditText editText = AmountEt2;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    AmountEt2.removeTextChangedListener(this);
                    AmountEt2.setText("");
                    AmountEt2.addTextChangedListener(this);
                    EditText editText2 = AmountEt2;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







        Toolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                calculate();
            }
        });



    }
    private void shareImage(Bitmap bitmap, Activity activity) {
        Uri uri;
        Date date = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        File createExternalStoragePrivateFile = createExternalStoragePrivateFile(bitmap, date.toString(), activity);
        Intent intent = new Intent("android.intent.action.SEND");
        if (Build.VERSION.SDK_INT < 23) {
            uri = Uri.parse(createExternalStoragePrivateFile.getAbsolutePath());
        } else {
            uri = FileProvider.getUriForFile(activity, activity.getBaseContext().getPackageName() + ".provider", createExternalStoragePrivateFile);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("android.intent.extra.STREAM", uri);
        activity.startActivity(Intent.createChooser(intent, "Share Image"));
    }


    private File createExternalStoragePrivateFile(Bitmap bitmap, String str, Context context) {
        File externalFilesDir = context.getExternalFilesDir("EMICalculator");
        File file = new File(externalFilesDir, str + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void calculate()
    {
        String str;
        String str2;
        String str3 = "";
        String replaceAll = AmountEt1.getText().toString().replaceAll(",", str3);
        Double valueOf = Double.valueOf(Double.parseDouble(replaceAll));
        String replaceAll2 = InterestEt2.getText().toString().replaceAll(",", str3);
        Double valueOf2 = Double.valueOf(Double.parseDouble(replaceAll2));
        String replaceAll3 = TenureEt1.getText().toString().replaceAll(",", str3);
        Double valueOf3 = Double.valueOf(Double.parseDouble(replaceAll3));
        String replaceAll4 = AmountEt2.getText().toString().replaceAll(",", str3);
        Double valueOf4 = Double.valueOf(Double.parseDouble(replaceAll4));
        String replaceAll5 = InterestEt2.getText().toString().replaceAll(",", str3);
        Double valueOf5 = Double.valueOf(Double.parseDouble(replaceAll5));
        String replaceAll6 = TenureEt2.getText().toString().replaceAll(",", str3);
        Double valueOf6 = Double.valueOf(Double.parseDouble(replaceAll6));
        if (valueOf.doubleValue() <= Utils.DOUBLE_EPSILON) {
            AmountEt1.setError("Enter the value more than zero");
            AmountEt1.requestFocus();
        } else if (valueOf4.doubleValue() <= Utils.DOUBLE_EPSILON) {
            AmountEt2.setError("Enter the value more than zero");
            AmountEt2.requestFocus();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf2.doubleValue() >= 100.0d) {
            InterestEt2.setError("Enter the value between 0.1 to 99.99");
            InterestEt2.requestFocus();
        } else if (valueOf2.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf5.doubleValue() >= 100.0d) {
            InterestEt2.setError("Enter the value between 0.1 to 99.99");
            InterestEt2.requestFocus();
        } else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf3.doubleValue() >= 600.0d) {
            TenureEt1.setError("Enter the month between 0 to 600");
            TenureEt1.requestFocus();
        } else if (valueOf3.doubleValue() <= Utils.DOUBLE_EPSILON || valueOf6.doubleValue() >= 600.0d) {
            TenureEt2.setError("Enter the month between 0 to 600");
            TenureEt2.requestFocus();
        }
        else if (TypeSpin1.getSelectedItemPosition() == 0 || TypeSpin2.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Choose Emi Type", Toast.LENGTH_SHORT).show();

        }else{

            String obj = TypeSpin1.getSelectedItem().toString();
            String obj2 = TypeSpin2.getSelectedItem().toString();
            if (obj.equals("Flat")) {
                str = utility_calculateLoanCompare.getFixedEMI(AmountEt1.getText().toString(), TenureEt1.getText().toString(), InterestEt1.getText().toString());
                TotalRepay1.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(this.utility_calculateLoanCompare.getFixedTotalRepayment(replaceAll, replaceAll3, replaceAll2),rs));
                Interesttv1.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(this.utility_calculateLoanCompare.getFixedInterest(replaceAll, replaceAll3, replaceAll2),rs));
                Emi1tv.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(str, rs));
            } else if (obj.equals("Reducing")) {
                str = utility_calculateLoanCompare.getEmiamount1(this.AmountEt1.getText().toString(), this.TenureEt1.getText().toString(), this.InterestEt1.getText().toString());
                TotalRepay1.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getTotalPayment(),rs));
                Interesttv1.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getTotalInterestPayable(),rs));
                Emi1tv.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(str,rs));
            }else {
                str = null;
            }
            if (obj2.equals("Flat")) {
                str2 = utility_calculateLoanCompare.getFixedEMI(this.AmountEt2.getText().toString(), this.TenureEt2.getText().toString(), this.InterestEt2.getText().toString());
                Emi2tv.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(str2,rs));
                TotalRepay2.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getFixedTotalRepayment(replaceAll4, replaceAll6, replaceAll5),rs));
                Interesttv2.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getFixedInterest(replaceAll4, replaceAll6, replaceAll5),rs));
            } else if (obj2.equals("Reducing")) {
                str2 = utility_calculateLoanCompare.getEmiamount1(this.AmountEt2.getText().toString(), this.TenureEt2.getText().toString(), this.InterestEt2.getText().toString());
                this.Emi2tv.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(str2,rs));
                this.TotalRepay2.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getTotalPayment(),rs));
                this.Interesttv2.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(utility_calculateLoanCompare.getTotalInterestPayable(),rs));
            } else {
                str2 = null;
            }
            double parseDouble = Double.parseDouble(str.replace(",", str3)) - Double.parseDouble(str2.replace(",", str3));
            this.EmiDiff.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.abs(parseDouble)),rs));
            String charSequence = this.Interesttv1.getText().toString();
            String charSequence2 = this.Interesttv2.getText().toString();
            String replace = charSequence.replace(",", str3);
            char[] charArray = charSequence2.replace(",", str3).toCharArray();
            String str4 = str3;
            for (int i = 1; i < charArray.length; i++) {
                str4 = str4 + charArray[i];
            }
            char[] charArray2 = replace.toCharArray();
            String str5 = str3;
            for (int i2 = 1; i2 < charArray2.length; i2++) {
                str5 = str5 + charArray2[i2];
            }
            double parseDouble2 = Double.parseDouble(str5.replaceAll(",", str3)) - Double.parseDouble(str4.replaceAll(",", str3));
            this.InterestDiff.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.abs(parseDouble2)),rs));
            String charSequence3 = this.TotalRepay1.getText().toString();
            String charSequence4 = this.TotalRepay2.getText().toString();
            String replace2 = charSequence3.replace(",", str3);
            String replace3 = charSequence4.replace(",", str3);
            char[] charArray3 = replace2.toCharArray();
            String str6 = str3;
            for (int i3 = 1; i3 < charArray3.length; i3++) {
                str6 = str6 + charArray3[i3];
            }
            char[] charArray4 = replace3.toCharArray();
            for (int i4 = 1; i4 < charArray4.length; i4++) {
                str3 = str3 + charArray4[i4];
            }
            double parseDouble3 = Double.parseDouble(str6) - Double.parseDouble(str3);
            this.TotalRepayDiff.setText(rs + " " + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.abs(parseDouble3)),rs));

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_nav1: {
                Intent i = new Intent(CompareLoanActivity.this, Emi_calculator.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav2: {
                Intent i = new Intent(CompareLoanActivity.this, CompareLoanActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav3: {
                Intent i = new Intent(CompareLoanActivity.this, Bt_topupActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav4: {
                Intent i = new Intent(CompareLoanActivity.this, Cheack_eligibilityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav5: {
                Intent i = new Intent(CompareLoanActivity.this, Current_roi_interestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav6: {
                Intent i = new Intent(CompareLoanActivity.this, DocumentActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav7: {
                Intent i = new Intent(CompareLoanActivity.this, EMI_perlakhsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav8: {
                Intent i = new Intent(CompareLoanActivity.this, InviteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav9: {
                Intent i = new Intent(CompareLoanActivity.this, FeedbackActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_nav10: {
                Intent i = new Intent(CompareLoanActivity.this, AboutusActivity.class);
                startActivity(i);
                break;
            }

        }


        return false;
    }
}