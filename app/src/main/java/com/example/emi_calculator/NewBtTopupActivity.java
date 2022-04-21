package com.example.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Databasehelper.BT_DBHistory;
import com.example.emi_calculator.Databasehelper.DB_BTHistory;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;
import com.example.emi_calculator.model.ModelBTHistory;
import com.example.emi_calculator.model.ModelNewBTHistory;

public class NewBtTopupActivity extends AppCompatActivity {
    EditText etTotalEMI,etSalary,etSanctionedAmount,etCurrentBalance,etEMIPaid,etBTAmount,etTopupAmount,etBTROI,etTopupROI,etBTEMI,etTopupEMI,etBTTenure,etTopupTenure,etFoir;
    Button btnSave,btnHistory;
    Utility_CalculateEMI utility_calculateEMI;
    ModelNewBTHistory modelBTHistory = new ModelNewBTHistory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bt_topup);
        utility_calculateEMI = new Utility_CalculateEMI();
        etSalary = findViewById(R.id.etSalary);
        etTotalEMI = findViewById(R.id.etTotalEMI);
        etSanctionedAmount = findViewById(R.id.etSanctionedAmount);
        etCurrentBalance = findViewById(R.id.etCurrentBalance);
        etEMIPaid = findViewById(R.id.etEMIPaid);
        etBTAmount = findViewById(R.id.etBTAmount);
        etTopupAmount = findViewById(R.id.etTopupAmount);
        etBTROI = findViewById(R.id.etBTROI);
        etTopupROI = findViewById(R.id.etTopupROI);
        etBTTenure = findViewById(R.id.etBTTenure);
        etTopupTenure = findViewById(R.id.etTopupTenure);
        etBTEMI = findViewById(R.id.etBTEMI);
        etTopupEMI = findViewById(R.id.etTopupEMI);
        etFoir = findViewById(R.id.etFoir);
        btnSave = findViewById(R.id.btnSave);
        btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBtTopupActivity.this,BTTopUpHistoryActivity.class);
                startActivity(intent);
            }
        });

        etSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etSalary.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etSalary.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etSalary.setText(trim2);
                    etSalary.addTextChangedListener(this);
                    EditText editText = etSalary;
                    editText.setSelection(editText.getText().toString().trim().length());
                    setFoirPercentage(etSalary.getText().toString().trim(),etBTEMI.getText().toString().trim(),etTopupEMI.getText().toString().trim());
                } else {
                    etSalary.removeTextChangedListener(this);
                    etSalary.setText("");
                    etSalary.addTextChangedListener(this);
                    EditText editText2 = etSalary;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSanctionedAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etSanctionedAmount.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etSanctionedAmount.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etSanctionedAmount.setText(trim2);
                    etSanctionedAmount.addTextChangedListener(this);
                    EditText editText = etSanctionedAmount;
                    editText.setSelection(editText.getText().toString().trim().length());
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    etSanctionedAmount.removeTextChangedListener(this);
                    etSanctionedAmount.setText("");
                    etSanctionedAmount.addTextChangedListener(this);
                    EditText editText2 = etSanctionedAmount;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCurrentBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etCurrentBalance.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etCurrentBalance.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etCurrentBalance.setText(trim2);
                    etCurrentBalance.addTextChangedListener(this);
                    EditText editText = etCurrentBalance;
                    editText.setSelection(editText.getText().toString().trim().length());
                    etBTAmount.setText(trim2);
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    etCurrentBalance.removeTextChangedListener(this);
                    etCurrentBalance.setText("");
                    etCurrentBalance.addTextChangedListener(this);
                    EditText editText2 = etCurrentBalance;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBTAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etBTAmount.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etBTAmount.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etBTAmount.setText(trim2);
                    etBTAmount.addTextChangedListener(this);
                    EditText editText = etBTAmount;
                    editText.setSelection(editText.getText().toString().trim().length());
                    setCalculateemiBT(etBTAmount.getText().toString().trim(),etBTROI.getText().toString().trim(),etBTTenure.getText().toString().trim());
                } else {
                    etBTAmount.removeTextChangedListener(this);
                    etBTAmount.setText("");
                    etBTAmount.addTextChangedListener(this);
                    EditText editText2 = etBTAmount;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBTROI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etBTROI.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    setCalculateemiBT(etBTAmount.getText().toString().trim(),etBTROI.getText().toString().trim(),etBTTenure.getText().toString().trim());


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etBTTenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etBTTenure.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    setCalculateemiBT(etBTAmount.getText().toString().trim(),etBTROI.getText().toString().trim(),etBTTenure.getText().toString().trim());


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etBTEMI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etBTEMI.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etBTEMI.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etBTEMI.setText(trim2);
                    etBTEMI.addTextChangedListener(this);
                    EditText editText = etBTEMI;
                    editText.setSelection(editText.getText().toString().trim().length());
                    setFoirPercentage(etSalary.getText().toString().trim(),etBTEMI.getText().toString().trim(),etTopupEMI.getText().toString().trim());
                    calculateEMI(etBTEMI.getText().toString().replaceAll(",", "").trim(),etTopupEMI.getText().toString().replaceAll(",", "").trim());
                } else {
                    etBTEMI.removeTextChangedListener(this);
                    etBTEMI.setText("");
                    etBTEMI.addTextChangedListener(this);
                    EditText editText2 = etBTEMI;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etTotalEMI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etTotalEMI.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etTotalEMI.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etTotalEMI.setText(trim2);
                    etTotalEMI.addTextChangedListener(this);
                    EditText editText = etTotalEMI;
                    editText.setSelection(editText.getText().toString().trim().length());
                } else {
                    etTotalEMI.removeTextChangedListener(this);
                    etTotalEMI.setText("");
                    etTotalEMI.addTextChangedListener(this);
                    EditText editText2 = etTotalEMI;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etTopupEMI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etTopupEMI.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etTopupEMI.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etTopupEMI.setText(trim2);
                    etTopupEMI.addTextChangedListener(this);
                    EditText editText = etTopupEMI;
                    editText.setSelection(editText.getText().toString().trim().length());
                    setFoirPercentage(etSalary.getText().toString().trim(),etBTEMI.getText().toString().trim(),etTopupEMI.getText().toString().trim());

                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    etTopupEMI.removeTextChangedListener(this);
                    etTopupEMI.setText("");
                    etTopupEMI.addTextChangedListener(this);
                    EditText editText2 = etTopupEMI;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etTopupAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etTopupAmount.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    etTopupAmount.removeTextChangedListener(this);
                    String trim2 = Constant_CurrencyFormat.rupeeFormat(trim).trim();
                    etTopupAmount.setText(trim2);
                    etTopupAmount.addTextChangedListener(this);
                    EditText editText = etTopupAmount;
                    editText.setSelection(editText.getText().toString().trim().length());
                    setCalculateemiTopUp(etTopupAmount.getText().toString().trim(),etTopupROI.getText().toString().trim(),etTopupTenure.getText().toString().trim());

                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

                } else {
                    etTopupAmount.removeTextChangedListener(this);
                    etTopupAmount.setText("");
                    etTopupAmount.addTextChangedListener(this);
                    EditText editText2 = etTopupAmount;
                    editText2.setSelection(editText2.getText().toString().trim().length());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTopupROI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etTopupROI.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    setCalculateemiTopUp(etTopupAmount.getText().toString().trim(),etTopupROI.getText().toString().trim(),etTopupTenure.getText().toString().trim());


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etTopupTenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trim = etTopupTenure.getText().toString().replaceAll(",", "").trim();
                if (trim.length() > 0) {
                    setCalculateemiTopUp(etTopupAmount.getText().toString().trim(),etTopupROI.getText().toString().trim(),etTopupTenure.getText().toString().trim());


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    setValuesBt();
                    SqliteManager dbHandler;
                    dbHandler = new SqliteManager(NewBtTopupActivity.this);
                    dbHandler.addItem(modelBTHistory);
                    reset();
                    Toast.makeText(NewBtTopupActivity.this, "Saved Succcessfully", Toast.LENGTH_SHORT).show();

                }


            }
        });




    }

    private void calculateEMI(String etBTEMI, String etTopupEMI)
    {
        if (etBTEMI != null && !etBTEMI.equals("") && etTopupEMI != null && !etTopupEMI.equals("")){
            int btemi = (int) Integer.parseInt(etBTEMI);
            int topemi = (int) Integer.parseInt(etTopupEMI);
            int totalemi = (int)btemi + topemi;
            etTotalEMI.setText(""+totalemi);

        }

    }

    private void reset() {
        etSalary.setText("");
        etSanctionedAmount.setText("");
        etCurrentBalance.setText("");
        etEMIPaid.setText("");
        etBTAmount.setText("");
        etTopupAmount.setText("");
        etBTROI.setText("");
        etTopupROI.setText("");
        etBTTenure.setText("");
        etTopupTenure.setText("");
        etBTEMI.setText("");
        etTotalEMI.setText("");
        etTopupEMI.setText("");
        etFoir.setText("");
    }


    private void setValuesBt()
    {
        modelBTHistory.setSalary(etSalary.getText().toString());
        modelBTHistory.setSanctionedAmount(etSanctionedAmount.getText().toString());
        modelBTHistory.setCurrentBalance(etCurrentBalance.getText().toString());
        modelBTHistory.setEMIPaid(etEMIPaid.getText().toString());
        modelBTHistory.setBTAmount(etBTAmount.getText().toString());
        modelBTHistory.setTopupAmount(etTopupAmount.getText().toString());
        modelBTHistory.setBTROI(etBTROI.getText().toString());
        modelBTHistory.setTopupROI(etTopupROI.getText().toString());
        modelBTHistory.setBTTenure(etBTTenure.getText().toString());
        modelBTHistory.setTopupTenure(etTopupTenure.getText().toString());
        modelBTHistory.setBTEMI(etBTEMI.getText().toString());
        modelBTHistory.setTopupEMI(etTopupEMI.getText().toString());
        modelBTHistory.setFoir(etFoir.getText().toString());

    }
    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }

    private void setCalculateemiBT(String btamt, String btroi, String bttenure)
    {
        if (btamt.length() > 0 && btroi.length() > 0 && bttenure.length() > 0){
            btamt = btamt.replaceAll(",","");
            btroi = btroi.replaceAll(",","");
            bttenure = bttenure.replaceAll(",","");

            String emiamount = utility_calculateEMI.getEmiamount(btamt, bttenure, btroi, "0");
            etBTEMI.setText(emiamount);


        }

    }
    private void setCalculateemiTopUp(String topupamt, String topuproi, String topuptenure)
    {
        if (topupamt.length() > 0 && topuproi.length() > 0 && topuptenure.length() > 0){
            topupamt = topupamt.replaceAll(",","");
            topuproi = topuproi.replaceAll(",","");
            topuptenure = topuptenure.replaceAll(",","");

            String emiamount = utility_calculateEMI.getEmiamount(topupamt,  topuptenure, topuproi,"0");
            etTopupEMI.setText(emiamount);

        }

    }
    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
    public void setFoirPercentage(String totalvalue, String btvalue,String topupvalue) {
        if (totalvalue.length() > 0 && btvalue.length() > 0 && topupvalue.length() > 0){
            double totalvalue2 = Integer.parseInt(btvalue.replaceAll(",",""))+Integer.parseInt(topupvalue.replaceAll(",",""));

            double total = Double.parseDouble(totalvalue.replaceAll(",",""));
            etFoir.setText(""+calculatePercentage(totalvalue2,total));



        }

    }
    private boolean validate()
    {
        if (TextUtils.isEmpty(etSalary.getText().toString())){
            etSalary.setError("Enter the Salary");
            etSalary.requestFocus();
            return false;
        }else if (TextUtils.isEmpty(etSanctionedAmount.getText().toString())){
            etSanctionedAmount.setError("Enter the  Sanctioned Amount");
            etSanctionedAmount.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etCurrentBalance.getText().toString())){
            etCurrentBalance.setError("Enter the  Current Balance");
            etCurrentBalance.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etEMIPaid.getText().toString())){
            etEMIPaid.setError("Enter the  EMI Paid");
            etEMIPaid.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etBTAmount.getText().toString())){
            etBTAmount.setError("Enter the BT Amount");
            etBTAmount.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etTopupAmount.getText().toString())){
            etTopupAmount.setError("Enter the Topup Amount");
            etTopupAmount.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etBTROI.getText().toString())){
            etBTROI.setError("Enter the BT ROI");
            etBTROI.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etTopupROI.getText().toString())){
            etTopupROI.setError("Enter the Top up ROI");
            etTopupROI.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etBTTenure.getText().toString())){
            etBTTenure.setError("Enter the BT Tenure");
            etBTTenure.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etTopupTenure.getText().toString())){
            etTopupTenure.setError("Enter the Topup Tenure");
            etTopupTenure.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etBTEMI.getText().toString())){
            etBTEMI.setError("Enter the BT EMI");
            etBTEMI.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etTotalEMI.getText().toString())){
            etTotalEMI.setError("Enter the Total EMI");
            etTotalEMI.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etTopupEMI.getText().toString())){
            etTopupEMI.setError("Enter the Topup EMI");
            etTopupEMI.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etFoir.getText().toString())){
            etFoir.setError("Enter the Foir");
            etFoir.requestFocus();
            return false;
        }
        return true;
    }

    public void toolbarclicked(View view) {
        onBackPressed();
    }
}