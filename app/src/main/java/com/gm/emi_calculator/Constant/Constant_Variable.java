package com.gm.emi_calculator.Constant;

import android.app.Activity;
import android.content.SharedPreferences;

public class Constant_Variable {
    public static String ASWDCEmailAddress = "aswdc@darshan.ac.in";
    public static String AdminMobileNo = "+91-97232 32741";
    public static String BT_HISTORY = "BTHISTORY";
    public static final String DATABASE_NAME = "EMICalculator.s3db";
    public static final int DBTABASE_VERSION = 13;
    public static String IS_EDIT = "EDIT";
    public static final int PRESSED_NO = 2;
    public static final int PRESSED_YES = 1;
    public static final String SHARE_MESSAGE = "Download 4.6★ rated App for EMI Calculation, Loan comparison with advanced feature like Processing Fees, GST on Interest, Fixed Rate etc.\nCalculated Using\nAndroid: http://app\n";

    public static String getShareAdvanceMessage(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("ShareMessage", 0);
        return "*EMI Calculation*\n \nLoan Amount: " + sharedPreferences.getString("loanamount", "₹10,00,000") + "\nReducing Rate: " + sharedPreferences.getString("reducingrate", "10%") + "\nFlat Rate: " + sharedPreferences.getString("flatrate", "1.9%") + "\nLoan Tenure : " + sharedPreferences.getString("tenure", " 5 Years (60 Months)") + "\nEMI: " + sharedPreferences.getString("eminAmount", "₹21,247") + "\nTotal Interest Payable: " + sharedPreferences.getString("totalintrestpayable", "₹94,743") + "\nGST On Interest: " + sharedPreferences.getString("gst", "₹17,054 (18%)") + "\nProcessing Fees: " + sharedPreferences.getString("processingfees", "₹1,16,797") + "\nGST On Processing Fees: " + sharedPreferences.getString("processingfeesGST", "₹1,16,797") + "\nTotal Payable Amount : " + sharedPreferences.getString("totalpayable", "₹11,16,797") + "\n \n" + SHARE_MESSAGE;
    }

    public static String getShareMessage(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("ShareMessage", 0);
        return "*EMI Calculation*\n \nLoan Amount: " + sharedPreferences.getString("loanamount", "₹10,00,000") + "\nInterest Rate: " + sharedPreferences.getString("intrestrate", "10 %") + "\nLoan Tenure : " + sharedPreferences.getString("tenure", " 5 Years (60 Months)") + "\n\nEMI: " + sharedPreferences.getString("eminAmount", "₹21,247") + "\nTotal Interest Payable: " + sharedPreferences.getString("totalintrestpayable", "₹2,74,820") + "\nTotal Payable Amount : " + sharedPreferences.getString("totalpayable", "₹12,74,820") + "\n\n" + SHARE_MESSAGE;
    }
}

