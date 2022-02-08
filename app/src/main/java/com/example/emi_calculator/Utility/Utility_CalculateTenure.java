package com.example.emi_calculator.Utility;

import com.example.emi_calculator.Constant.Constant_CurrencyFormat;
import com.github.mikephil.charting.utils.Utils;

public class Utility_CalculateTenure {

    /* renamed from: a */
    double f3793a;

    /* renamed from: b */
    double f3794b;

    /* renamed from: c */
    double f3795c;

    /* renamed from: d */
    double f3796d;

    /* renamed from: e */
    double f3797e;

    /* renamed from: f */
    double f3798f;

    /* renamed from: g */
    double f3799g;

    /* renamed from: h */
    int f3800h = 0;

    public String getTotalPayable(String str) {
        return (this.f3793a + this.f3795c) + "";
    }

    public String gettenure(String str, String str2, String str3) {
        String replaceAll = str.replaceAll(",", "");
        String replaceAll2 = str2.replaceAll(",", "");
        this.f3795c = Utils.DOUBLE_EPSILON;
        this.f3796d = Utils.DOUBLE_EPSILON;
        this.f3797e = Utils.DOUBLE_EPSILON;
        this.f3798f = Utils.DOUBLE_EPSILON;
        this.f3799g = Utils.DOUBLE_EPSILON;
        this.f3800h = 0;
        this.f3793a = Utils.DOUBLE_EPSILON;
        this.f3794b = Utils.DOUBLE_EPSILON;
        new Constant_CurrencyFormat();
        this.f3795c = Double.parseDouble(replaceAll);
        this.f3796d = Double.parseDouble(replaceAll2);
        this.f3794b = (Double.parseDouble(str3) / 12.0d) / 100.0d;
        this.f3797e = this.f3795c;
        while (true) {
            double d = this.f3797e;
            if (d > Utils.DOUBLE_EPSILON) {
                double round = (double) Math.round(this.f3794b * d);
                this.f3799g = round;
                this.f3793a += round;
                double round2 = (double) Math.round(this.f3796d - round);
                this.f3798f = round2;
                this.f3797e = (double) Math.round(this.f3797e - round2);
                this.f3800h++;
            } else {
                return this.f3800h + "";
            }
        }
    }
}

