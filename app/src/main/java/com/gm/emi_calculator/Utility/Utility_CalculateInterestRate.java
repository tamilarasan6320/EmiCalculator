package com.gm.emi_calculator.Utility;

import com.gm.emi_calculator.Constant.Constant_CurrencyFormat;
import com.github.mikephil.charting.utils.Utils;

import java.text.DecimalFormat;

public class Utility_CalculateInterestRate {

    /* renamed from: a */
    double f3782a;

    /* renamed from: b */
    int f3783b;

    /* renamed from: c */
    double f3784c;

    /* renamed from: d */
    double f3785d;

    /* renamed from: e */
    double f3786e;

    /* renamed from: f */
    double f3787f;

    /* renamed from: g */
    double f3788g;

    public String getInterestrate(String str, String str2, String str3) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        String replaceAll = str.replaceAll(",", "");
        String replaceAll2 = str3.replaceAll(",", "");
        this.f3782a = Utils.DOUBLE_EPSILON;
        this.f3784c = Utils.DOUBLE_EPSILON;
        this.f3785d = Utils.DOUBLE_EPSILON;
        double d = 100.0d;
        this.f3786e = 100.0d;
        this.f3787f = Utils.DOUBLE_EPSILON;
        this.f3788g = Utils.DOUBLE_EPSILON;
        new Constant_CurrencyFormat();
        this.f3785d = Double.parseDouble(replaceAll);
        this.f3783b = Integer.parseInt(str2);
        this.f3787f = (double) Math.round(Double.parseDouble(replaceAll2));
        while (true) {
            if (this.f3787f == this.f3782a) {
                break;
            }
            double d2 = (this.f3786e / 12.0d) / d;
            double pow = Math.pow(d2 + 1.0d, (double) this.f3783b);
            double round = (double) Math.round(((this.f3785d * d2) * pow) / (pow - 1.0d));
            this.f3782a = round;
            double d3 = this.f3787f;
            if (round > d3) {
                double d4 = this.f3786e;
                this.f3788g = d4;
                this.f3786e = d4 / 2.0d;
            } else if (round < d3) {
                this.f3786e = (this.f3788g + this.f3786e) / 2.0d;
            }
            if (this.f3786e < 1.0d) {
                this.f3786e = Utils.DOUBLE_EPSILON;
                break;
            }
            d = 100.0d;
        }
        String format = decimalFormat.format(this.f3786e);
        return "" + format;
    }

    public double getROI(String str, String str2, String str3) {
        String replaceAll = str.replaceAll(",", "");
        String replaceAll2 = str3.replaceAll(",", "");
        this.f3782a = Utils.DOUBLE_EPSILON;
        this.f3784c = Utils.DOUBLE_EPSILON;
        this.f3785d = Utils.DOUBLE_EPSILON;
        this.f3786e = 100.0d;
        this.f3787f = Utils.DOUBLE_EPSILON;
        this.f3788g = Utils.DOUBLE_EPSILON;
        new Constant_CurrencyFormat();
        this.f3785d = Double.parseDouble(replaceAll);
        this.f3783b = Integer.parseInt(str2);
        this.f3787f = Double.parseDouble(replaceAll2);
        while (true) {
            if (this.f3787f == this.f3782a) {
                break;
            }
            double d = (this.f3786e / 12.0d) / 100.0d;
            double pow = Math.pow(d + 1.0d, (double) this.f3783b);
            double d2 = ((this.f3785d * d) * pow) / (pow - 1.0d);
            this.f3782a = d2;
            double d3 = this.f3787f;
            if (d2 > d3) {
                double d4 = this.f3786e;
                this.f3788g = d4;
                this.f3786e = d4 / 2.0d;
            } else if (d2 < d3) {
                this.f3786e = (this.f3788g + this.f3786e) / 2.0d;
            }
            if (this.f3786e < 1.0d) {
                this.f3786e = Utils.DOUBLE_EPSILON;
                break;
            }
        }
        return this.f3786e;
    }

    public String getTotalInterestPayable() {
        double d = (this.f3782a * ((double) this.f3783b)) - this.f3785d;
        return "" + Constant_CurrencyFormat.format(d);
    }

    public String getTotalPayment() {
        this.f3784c = this.f3782a * ((double) this.f3783b);
        return "" + Constant_CurrencyFormat.format(this.f3784c);
    }
}

