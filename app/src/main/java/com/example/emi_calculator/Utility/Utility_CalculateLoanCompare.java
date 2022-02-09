package com.example.emi_calculator.Utility;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.github.mikephil.charting.utils.Utils;

public class Utility_CalculateLoanCompare {

    /* renamed from: a */
    double f3789a;

    /* renamed from: b */
    int f3790b;

    /* renamed from: c */
    double f3791c;

    /* renamed from: d */
    double f3792d;

    public String getEmiamount1(String str, String str2, String str3) {
        this.f3789a = Utils.DOUBLE_EPSILON;
        this.f3791c = Utils.DOUBLE_EPSILON;
        this.f3792d = Utils.DOUBLE_EPSILON;
        new Constant_CurrencyFormat();
        this.f3792d = Double.parseDouble(str.replaceAll(",", ""));
        this.f3790b = Integer.parseInt(str2);
        double parseDouble = (Double.parseDouble(str3) / 12.0d) / 100.0d;
        double pow = Math.pow(parseDouble + 1.0d, (double) this.f3790b);
        double round = (double) Math.round(((this.f3792d * parseDouble) * pow) / (pow - 1.0d));
        this.f3789a = round;
        return Constant_CurrencyFormat.format(round);
    }

    public String getFixedEMI(String str, String str2, String str3) {
        String replaceAll = str.replaceAll(",", "").replaceAll("₹", "").replaceAll("$", "");
        this.f3792d = Double.parseDouble(replaceAll);
        double parseDouble = (this.f3792d + Double.parseDouble(getFixedInterest(replaceAll, str2, str3))) / ((double) Integer.parseInt(str2));
        return "" + parseDouble;
    }

    public String getFixedInterest(String str, String str2, String str3) {
        return "" + ((((Double.parseDouble(str.replaceAll(",", "").replaceAll("₹", "").replaceAll("$", "")) * Double.parseDouble(str3)) / 100.0d) * ((double) Integer.parseInt(str2))) / 12.0d);
    }

    public String getFixedTotalRepayment(String str, String str2, String str3) {
        String replaceAll = str.replaceAll(",", "").replaceAll("₹", "").replaceAll("$", "");
        this.f3792d = Double.parseDouble(replaceAll);
        String fixedInterest = getFixedInterest(replaceAll, str2, str3);
        return "" + (this.f3792d + Double.parseDouble(fixedInterest));
    }

    public String getTotalInterestPayable() {
        return Constant_CurrencyFormat.format(this.f3791c - this.f3792d);
    }

    public String getTotalPayment() {
        double d = this.f3789a * ((double) this.f3790b);
        this.f3791c = d;
        return Constant_CurrencyFormat.format(d);
    }
}

