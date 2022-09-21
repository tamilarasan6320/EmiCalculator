package com.gm.emi_calculator.Utility;

import com.gm.emi_calculator.Constant.Constant_CurrencyFormat;

public class Utility_CalculateAmount {

    /* renamed from: a */
    int f3774a;

    /* renamed from: b */
    double f3775b;

    /* renamed from: c */
    double f3776c;

    /* renamed from: d */
    double f3777d;

    public String getTotalInterestPayable() {
        return Constant_CurrencyFormat.format((this.f3777d * ((double) this.f3774a)) - ((double) Math.round(this.f3776c)));
    }

    public String getTotalPayment() {
        double d = this.f3777d * ((double) this.f3774a);
        this.f3775b = d;
        return Constant_CurrencyFormat.format(d);
    }

    public double getamount(String str, String str2, String str3) {
        new Constant_CurrencyFormat();
        this.f3777d = Double.parseDouble(str.replaceAll(",", ""));
        this.f3774a = Integer.parseInt(str2);
        double parseDouble = (Double.parseDouble(str3) / 12.0d) / 100.0d;
        double d = (double) this.f3774a;
        double pow = Math.pow(parseDouble + 1.0d, d);
        double d2 = this.f3777d;
        double d3 = ((pow - 1.0d) * d2) / (parseDouble * pow);
        this.f3776c = d3;
        Math.ceil((d2 * d) - d3);
        return this.f3776c;
    }
}

