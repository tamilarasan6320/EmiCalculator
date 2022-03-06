package com.example.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.example.emi_calculator.Utility.Utility_CalculateEMI;

public class NewBtTopupActivity extends AppCompatActivity {
    EditText etSalary,etSanctionedAmount,etCurrentBalance,etEMIPaid,etBTAmount,etTopupAmount,etBTROI,etTopupROI,etBTEMI,etTopupEMI,etBTTenure,etTopupTenure,etFoir;
    Button btnSave;
    Utility_CalculateEMI utility_calculateEMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bt_topup);
        utility_calculateEMI = new Utility_CalculateEMI();

        etSalary = findViewById(R.id.etSalary);
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
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

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
                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

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

                    //rupeeswords.setText(Constant_NumToWord_Rupee.convertNumberToWords(Long.parseLong(clearFormet(trim2))));

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




    }

    private void setCalculateemiBT(String btamt, String btroi, String bttenure)
    {
        if (btamt.length() > 0 && btroi.length() > 0 && bttenure.length() > 0){
            btamt = btamt.replaceAll(",","");
            btroi = btroi.replaceAll(",","");
            bttenure = bttenure.replaceAll(",","");

            String emiamount = utility_calculateEMI.getEmiamount(btamt, bttenure, btroi, "0");
            etBTEMI.setText(emiamount);


            //Log.d("NEWBTCAL",btamt+btroi+bttenure);

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


            //Log.d("NEWBTCAL",btamt+btroi+bttenure);

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

    public void toolbarclicked(View view) {
        onBackPressed();
    }
}