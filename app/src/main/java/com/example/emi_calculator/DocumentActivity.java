package com.example.emi_calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DocumentActivity extends AppCompatActivity {


    TextView salaried,selfemployed,doctitle1,docsubtitle1,doctitle2,docsubtitle2,doctitle3,docsubtitle3;
    String Loan;
    ImageButton Toolbtn;
    LinearLayout title;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        Toolbtn = findViewById(R.id.toolbar);
        tv1 = findViewById(R.id.tv1);


        Toolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        Loan = getIntent().getStringExtra("loan");


        salaried =findViewById(R.id.salaried);
        selfemployed =findViewById(R.id.selfemployed);
        doctitle1 =findViewById(R.id.doctitle1);
        docsubtitle1 =findViewById(R.id.docsubtitle1);
        doctitle2 =findViewById(R.id.doctitle2);
        docsubtitle2 =findViewById(R.id.docsubtitle2);
        doctitle3 =findViewById(R.id.doctitle3);
        docsubtitle3 =findViewById(R.id.docsubtitle3);
        title = findViewById(R.id.title);


        tv1.setText(Loan);
        if (Loan.equals("mortigage_loan")){
            salariedMortageLoan();

        }
        else if (Loan.equals("bt_mortigage_loan")){
            salariedBtMortageLoan();

        }
        else if (Loan.equals("home_loan")){
            salariedHomeLoan();

        }
        else if (Loan.equals("bt_home_loan")){
            salariedBtHomeLoan();

        }
        else if (Loan.equals("business_loan")){
            title.setVisibility(View.GONE);
            salaried.setVisibility(View.GONE);
            selfemployed.setVisibility(View.GONE);

            businessLoan();

        }
        salaried.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                selfemployed.setTextColor(Color.WHITE);
                selfemployed.setBackgroundResource(R.drawable.btn_right);
             //   selfemployed.setBackgroundColor(getColor(R.color.primaryDarkColor));
                salaried.setTextColor(getColor(R.color.primaryDarkColor));
                salaried.setBackgroundColor(getColor(R.color.white));
                salaried.setBackgroundResource(R.drawable.btn_left);
                if (Loan.equals("mortigage_loan")){
                    salariedMortageLoan();


                }
                else if (Loan.equals("bt_mortigage_loan")){
                    salariedBtMortageLoan();


                }
                else if (Loan.equals("home_loan")){
                    salariedHomeLoan();

                }
                else if (Loan.equals("bt_home_loan")){
                    salariedBtHomeLoan();

                }


            }
        });
        selfemployed.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                salaried.setTextColor(Color.WHITE);
                salaried.setBackgroundResource(R.drawable.btn_left_clicked);
                //  salaried.setBackgroundColor(getColor(R.color.primaryDarkColor));
                selfemployed.setTextColor(getColor(R.color.primaryDarkColor));
                selfemployed.setBackgroundColor(getColor(R.color.white));
                selfemployed.setBackgroundResource(R.drawable.btn_right_click);

                if (Loan.equals("mortigage_loan")){
                    doctitle1.setText("PERSONAL & INCOME DOCUMENTS");
                    doctitle2.setText("PROPERTY DOCUMENTS");
                    docsubtitle1.setText("1  PAN AND ADHAR CARD\n" +
                            "2  Co applicant Pan and adhar card\n" +
                            "3  present staying address proof\n" +
                            "4  Registration proof\n" +
                            "5  GST Reg proof (if available)\n" +
                            "6  Vintage proof\n" +
                            "7  Latest 3 years IT returns\n" +
                            "8  1 year bank statement of current and saving\n" +
                            "account");
                    docsubtitle2.setText("1 Sale deed\n" +
                            "2 15 years Link documents\n" +
                            "3 property tax receipts\n" +
                            "4 PLAN COPY\n" +
                            "5 EC");

                }
                else if (Loan.equals("bt_mortigage_loan")){
                    doctitle1.setText("PROPERITOR SHIP");
                    doctitle2.setText("PROPERTY DOCUMENTS");
                    docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                            "2 Co applicant Pan and adhar card\n" +
                            "3 present staying address proof\n" +
                            "4 Registration proof\n" +
                            "5 GST Reg proof (if available)\n" +
                            "6 Vintage proof\n" +
                            "7 Latest 3 years IT returns\n" +
                            "8 1 year bank statement of current and saving account");
                    docsubtitle2.setText("1 Sale deed\n" +
                            "2 15 years Link documents\n" +
                            "3 property tax receipts\n" +
                            "4 PLAN COPY\n" +
                            "5 EC\n" +
                            "6 sanction Letter\n" +
                            "7 List of documents\n" +
                            "8 Out standing letter\n" +
                            "9 Loan account staement");

                }
                else if (Loan.equals("home_loan")){
                    doctitle1.setText("PROPERITOR SHIP");
                    doctitle2.setText("PROPERTY DOCUMENTS");
                    docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                            "2 Co applicant Pan and adhar card\n" +
                            "3 present staying address proof\n" +
                            "4 Registration proof\n" +
                            "5 GST Reg proof (if available)\n" +
                            "6 Vintage proof\n" +
                            "7 Latest 3 years IT returns\n" +
                            "8 1 year bank statement of current and saving\n" +
                            "account");
                    docsubtitle2.setText("1 Agreement of sale\n" +
                            "2 Sale deed\n" +
                            "3 15 years Link documents\n" +
                            "\n" +
                            "4 property tax receipts\n" +
                            "5 PLAN COPY\n" +
                            "6 EC");

                }
                else if (Loan.equals("bt_home_loan")){
                    doctitle1.setText("PROPERITOR SHIP");
                    doctitle2.setText("PROPERTY DOCUMENTS");
                    docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                            "2 Co applicant Pan and adhar card\n" +
                            "3 present staying address proof\n" +
                            "4 Registration proof\n" +
                            "5 GST Reg proof (if available)\n" +
                            "6 Vintage proof\n" +
                            "7 Latest 3 years IT returns\n" +
                            "8 1 year bank statement of current and saving account");
                    docsubtitle2.setText("1 Sale deed\n" +
                            "2 15 years Link documents\n" +
                            "3 property tax receipts\n" +
                            "4 PLAN COPY\n" +
                            "5 EC\n" +
                            "6 sanction Letter\n" +
                            "7 List of documents\n" +
                            "8 Out standing letter\n" +
                            "9 Loan account staement");

                }


            }
        });


    }

    private void businessLoan()
    {
        doctitle1.setText("Proprietor ship");
        doctitle2.setText("Partner ship firm");
        doctitle3.setText("Private Limited OR Limited");
        docsubtitle1.setText("1. Pan card and Aadhar card of both Applicant and Co-applicant\n" +
                "2. Present resident address proof of both Applicant and Co-applicant\n" +
                "3. GST Registration copy or Labour license Vintage proof\n" +
                "4. GST Returns one-year req\n" +
                "5. Present office address proof\n" +
                "6. Own Property proof ie (latest month electricity bill, property tax, sale deed )\n" +
                "7. Latest 2 years ITR returns\n" +
                "8. Latest one-year bank statement to till date in PDF format of all bank’s\n" +
                "9. If any loan’s track req\n" +
                "10. pass port size photo of both Applicant and Co-applicant\n" +
                "11. official mail id\n" +
                "12. Two References personal references and Trade references");
        docsubtitle2.setText("1. All Partners Pan card and Aadhar card\n" +
                "2. All Partners Present resident address proof\n" +
                "3. Company Pan card\n" +
                "4. GST Registration copy\n" +
                "5. Partnership deed\n" +
                "6. Present office address proof\n" +
                "7. Own Property proof ie (latest month electricity bill , property tax, sale deed )\n" +
                "8. Latest 2 years ITR returns of company and individual\n" +
                "9. Latest one-year bank statement to till date in PDF format of all bank’s\n" +
                "10. All Partners pass port size photo\n" +
                "11. Company mail id\n" +
                "12. Two References personal references and Trade references\n" +
                "\n" +
                "13. If any loan’s track req");
        docsubtitle3.setText("1. All Director’s Pan card and Aadhar card\n" +
                "2. All Director’s Present resident address proof\n" +
                "3. Company Pan card\n" +
                "4. GST Registration copy\n" +
                "5. incorporation certificate\n" +
                "6. MOA and AOA\n" +
                "7. Share holding pattern\n" +
                "8. Present office address proof\n" +
                "9. Own Property proof ie (latest month electricity bill , property tax, sale deed )\n" +
                "10. Latest 2 years ITR returns of company and individual\n" +
                "11. Latest one-year bank statement to till date in PDF format of all bank’s\n" +
                "12. All Director pass port size photo\n" +
                "13. Company mail id\n" +
                "14. Two References personal references and Trade references\n" +
                "15. If any loan’s track req");

    }

    private void salariedHomeLoan()
    {
        doctitle1.setText("PERSONAL & INCOME DOCUMENTS");
        doctitle2.setText("PROPERTY DOCUMENTS");
        docsubtitle1.setText("PAN AND ADHAR CARD\n" +
                "Co-applicant Pan and adhar card\n" +
                "Present staying address proof\n" +
                "6 Months pay slip\n" +
                "1 year Bank statement\n" +
                "2 years Form-16\n" +
                "Login Fees cheque");
        docsubtitle2.setText("AGREEMENT OF SALE\n" +
                "Sale deed\n" +
                "15 years Link documents\n" +
                "property tax receipts\n" +
                "PLAN COPY\n" +
                "EC");

    }

    private void salariedBtHomeLoan()
    {
        doctitle1.setText("PERSONAL & INCOME DOCUMENTS");
        doctitle2.setText("PROPERTY DOCUMENTS");
        docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                "2 Co-applicant Pan and adhar card\n" +
                "3 Present staying address proof\n" +
                "4 6 Months pay slip\n" +
                "5 1 year Bank statement\n" +
                "6 2 years Form-16\n" +
                "7 Login Fees cheque");
        docsubtitle2.setText("1 Sale deed\n" +
                "2 15 years Link documents\n" +
                "3 property tax receipts\n" +
                "4 PLAN COPY\n" +
                "5 EC\n" +
                "6 sanction Letter\n" +
                "7 List of documents\n" +
                "8 Outstanding letter\n" +
                "9 Loan account statement");

    }

    private void salariedBtMortageLoan()
    {
        doctitle1.setText("PERSONAL & INCOME DOCUMENTS");
        doctitle2.setText("PROPERTY DOCUMENTS");
        docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                "2 Co-applicant Pan and adhar card\n" +
                "3. PRESENT STAYING ADDRESS PROOF\n" +
                "4 6 Months pay slip\n" +
                "5 1 year Bank statement\n" +
                "6 2 years Form-16\n" +
                "7 Login Fees cheque");
        docsubtitle2.setText("1 Sale deed\n" +
                "2 15 years Link documents\n" +
                "3 property tax receipts\n" +
                "4 PLAN COPY\n" +
                "5 EC\n" +
                "6 sanction Letter\n" +
                "7 List of documents\n" +
                "8 Out standing letter\n" +
                "9 Loan account staement");

    }

    private void salariedMortageLoan() {
        doctitle1.setText("PERSONAL & INCOME DOCUMENTS");
        doctitle2.setText("PROPERTY DOCUMENTS");
        docsubtitle1.setText("1 PAN AND ADHAR CARD\n" +
                "2 Co-applicant Pan and adhar card\n" +
                "3 Present staying address proof\n" +
                "4 6 Months pay slip\n" +
                "5 1 year Bank statement\n" +
                "6 2 years Form-16\n" +
                "7 Login Fees cheque");
        docsubtitle2.setText("1 Sale deed\n" +
                "2 15 years Link documents\n" +
                "3 property tax receipts\n" +
                "4 PLAN COPY\n" +
                "5 EC");
    }

}