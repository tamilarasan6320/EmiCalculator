package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.gm.emi_calculator.adapter.FAQAdapter;
import com.gm.emi_calculator.model.FAQS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FaqsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getFAQS();



    }

    private void getFAQS()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<FAQS>>(){}.getType();
        List<FAQS> faqslist = gson.fromJson(faqsString, type);
        FAQAdapter productAdapter = new FAQAdapter(faqslist,FaqsActivity.this);
        recyclerView.setAdapter(productAdapter);
    }
    private String faqsString = "[\n" +
            "        {\n" +
            "                \"id\": \"1\",\n" +
            "                \"question\": \"What are the key steps in the loan approval process?\",\n" +
            "                \"answer\": \"Approval of loan is at the sole discretion of the loan sanctioning officer who bases his/her\n" +
            "decision on the basis of the criteria specified by the bank/ financial institution. The entire\n" +
            "process can take from about 48 hours. Once all the necessary documents are submitted and\n" +
            "the verification process is completed, the loan, if sanctioned, is disbursed by the bank. In\n" +
            "order to avoid delays in loan processing and disbursement, do keep all necessary documents\n" +
            "ready along with the post dated checks (PDC) and/or signed Electronic Clearing System\n" +
            "form.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"2\",\n" +
            "                \"question\": \"How to decide which bank/financial institution to take the loan from?\",\n" +
            "                \"answer\": \"It is always a good idea to compare the offers of individual banks before you decide to settle\n" +
            "on a specific provider. Use online tools like the loan eligibility calculator and personal loan\n" +
            "EMI calculator to find the loan option that suits you the best. Some of the key factors to\n" +
            "consider when deciding on a loan provider include interest rates, loan tenure, processing fees\n" +
            "and others.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"3\",\n" +
            "                \"question\": \"Should I always choose the lowest possible EMI when choosing my loan provider?\",\n" +
            "                \"answer\": \"Low EMI offers can typically result from a low interest rate, a long repayment term or a\n" +
            "combination of the two factors. Thus in some cases, you might end up paying a lot more to\n" +
            "your lender as interest if you choose the lower EMI option. Instead use online tools like the\n" +
            "personal loan EMI calculator to figure out your interest payout over the loan tenure and your\n" +
            "repayment capacity before making your decision.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"4\",\n" +
            "                \"question\": \"How do I compare the interest rates of personal loans?\",\n" +
            "                \"answer\": \"Log on to Paisabazaar.com and fill out our personal loan eligibility tool to get a list of all\n" +
            "available personal loan option along with key data such as applicable interest rate, processing\n" +
            "fees as well as information about other charges such as pre-payment charges. Using these\n" +
            "data, you can easily compare the various personal loan options available with multiple banks\n" +
            "and NBFCs.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"5\",\n" +
            "                \"question\": \"How do I get the lowest interest rates on personal loan?\",\n" +
            "                \"answer\": \"Earlier, you might have had to go to multiple lenders in order to get the information regarding\n" +
            "interest rates, tenures, processing fees etc. Not anymore. Just log on to Paisabazaar.com and\n" +
            "fill out the simple form in our Personal Loan sections and in seconds you get information\n" +
            "regarding numerous prospective lenders along with the applicable interest rates and various\n" +
            "charges including processing fees. You can easily choose the lowest interest rate from the\n" +
            "available list and apply for a personal loan within minutes from the convenience of your\n" +
            "home or workplace.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"6\",\n" +
            "                \"question\": \"Are personal loan interest rates fixed or floating?\",\n" +
            "                \"answer\": \"In case of a fixed rate personal loan, your EMI amount remains fixed therefore every month\n" +
            "during the loan tenure, you will pay the exact same amount as EMI. In case of a floating rate\n" +
            "personal loan, the EMI amount will keep decreasing as it follows the reducing balance\n" +
            "method of calculating interest payout on a personal loan. In case of a floating rate, the\n" +
            "\n" +
            "applicable interest rate may be varied by the bank periodically as per the new MCLR rules,\n" +
            "floating interest rates may be changed either on a half yearly or yearly basis.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"7\",\n" +
            "                \"question\": \"What is relationship discount?\",\n" +
            "                \"answer\": \"Relationship discount is an additional benefit that is provided by the banks or lenders to a\n" +
            "prospective applicant if they have a pre-existing relationship with the lender. Such pre-\n" +
            "existing relationship may include having a salary/savings account with the bank or having an\n" +
            "existing credit card, fixed deposit or loan with the prospective lender.  Relationship discounts\n" +
            "may result in the lender providing you with discounts when applying for the loan like –\n" +
            "waiver of the processing fees, lower interest rates or others.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"8\",\n" +
            "                \"question\": \"How is the personal loan disbursed?\",\n" +
            "                \"answer\": \"Once you get approved for a personal loan, you may either receive an account payee\n" +
            "cheque/draft equal to the loan amount or get the money deposited automatically into your\n" +
            "savings account electronically.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"9\",\n" +
            "                \"question\": \"Which are the best banks and NBFCs for a personal loan?\",\n" +
            "                \"answer\": \"Personal loans are offered by all leading banks and numerous NBFCs (non-banking financial\n" +
            "companies). Some of the leading providers of personal loans include HDFC Bank, IndusInd\n" +
            "Bank, Citibank, Axis Bank, Fullerton India, Capital First, State Bank of India and Associates,\n" +
            "Indian Bank, Karnataka Bank, Standard Chartered Bank and many others.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"10\",\n" +
            "                \"question\": \"How to repay the personal loan?\",\n" +
            "                \"answer\": \"The loan can be repaid in the form of Equated Monthly Installment (EMI) via post-dated\n" +
            "cheques   drawn in favour of the bank or by releasing a mandate allowing payment through\n" +
            "the Electronic Clearing Services (ECS) system.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"11\",\n" +
            "                \"question\": \"What is the minimum credit score to get a personal loan?\",\n" +
            "                \"answer\": \"It depends on the eligibility criteria set by the lender. Most lenders do not specify a\n" +
            "minimum credit score for a personal loan. Some lenders might lend to applicants with low\n" +
            "credit score (less than 750) but the interest rate applicable is usually higher in such cases.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"12\",\n" +
            "                \"question\": \"What is the minimum salary required to get a personal loan?\",\n" +
            "                \"answer\": \"The minimum monthly salary required to avail personal loan varies from lender to\n" +
            "lender. However, for large lenders like private and public sector banks, the minimum income\n" +
            "eligibility is Rs. 15,000 per month and above.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"13\",\n" +
            "                \"question\": \"Can I get a personal loan being a pensioner, if I have a pension account with one of the\n" +
            "leading banks in India?\",\n" +
            "                \"answer\": \"Yes, you can get a personal loan even as a pensioner, if you have a pension account\n" +
            "with one of the leading banks. However, you should ensure that the bank where you receive\n" +
            "pension funds offers personal loans to pensioners and you meet the eligibility criteria as\n" +
            "specified by your prospective lender.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"14\",\n" +
            "                \"question\": \"How to choose the ideal repayment tenor for business loans?\",\n" +
            "                \"answer\": \"Ideally, if you avail short-term loan then the repayment tenure should not exceed 12\n" +
            "months. However, it may increase as per the desired loan amount. The maximum repayment\n" +
            "period can be chosen up to 5 years depending upon the loan amount.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"15\",\n" +
            "                \"question\": \"What is the minimum turnover requirement for a loan to start a business?\",\n" +
            "                \"answer\": \"The minimum annual turnover criteria is defined by the lender and vary from bank to\n" +
            "bank. However, applicants shall apply online with a minimum annual turnover of Rs. 12 lakh\n" +
            "or above for existing enterprises or businesses.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"16\",\n" +
            "                \"question\": \"What are the pre-closure and part-prepayment charges in business loans?\",\n" +
            "                \"answer\": \"The pre-closure and part-payment charges vary from lender to lender. It may be Nil\n" +
            "from some banks and may exceed up to 5% of the loan amount from others. Ensure to check\n" +
            "the same with your lender.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"17\",\n" +
            "                \"question\": \"Which bank is the best for home loan?\",\n" +
            "                \"answer\": \"Some of the most popular banks offering home loans in india are HDFC Bank, SBI, PNB, ICIC Bank, Bank of Baroda,\n" +
            "Axis Bank and Canara Bank. However, the best home loan for you would be the one that matches your loan requirements.\n" +
            "Therefore, to get the best bank for a home loan first analysis your requirements. Also, when comparing home loan offers don't\n" +
            "jump for the offer that offers the lowest interest rate,rather check on the entire deal, Besides the interest rate, pay\n" +
            "attenation to other parameters such as processing fees and the loan repayment and prepayment policies.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"18\",\n" +
            "                \"question\": \"Are there any prepayment charges in case of a home loan?\",\n" +
            "                \"answer\": \"In the case of a floating rate home loan, lenders don't change a pre-payment\n" +
            "penalty as per RBI directives however a penalty may be applied in case of prepayment\n" +
            "of a fixed-rate home loan.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"19\",\n" +
            "                \"question\": \"What types of properties are accepted by lenders providing Loan Against Property\n" +
            "(LAP)?\",\n" +
            "                \"answer\": \"Different lenders have different criteria for the type of property to be accepted against a\n" +
            "mortgage loan. However,mostly all financial institutions accept the residential, commercial\n" +
            "or industrial property. It is important to note that the physical condition and age of the\n" +
            "property may affect its acceptance by the financial institution.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"19\",\n" +
            "                \"question\": \"Can NRIs avail loans against the property?\",\n" +
            "                \"answer\": \"Yes, there are several financial institutions that offer loan against property to NRIs.\",\n" +
            "                \"sample\": {\n" +
            "                }\n" +
            "        }\n" +
            "    ]";


}