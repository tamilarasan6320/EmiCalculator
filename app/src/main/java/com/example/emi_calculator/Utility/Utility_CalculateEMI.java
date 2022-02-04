package com.example.emi_calculator.Utility;

import android.util.Log;

import com.github.mikephil.charting.utils.Utils;

import java.text.DecimalFormat;

public class Utility_CalculateEMI {

    /* renamed from: a */
    int f3778a;

    /* renamed from: b */
    double f3779b;

    /* renamed from: c */
    double f3780c;

    /* renamed from: d */
    double f3781d = Utils.DOUBLE_EPSILON;

    public String getEmiamount(String str, String str2, String str3, String str4) {
        this.f3780c = Double.parseDouble(str.replaceAll(",", ""));
        this.f3778a = Integer.parseInt(str2);
        this.f3781d = Double.parseDouble(str4);
        double parseDouble = (Double.parseDouble(str3) / 12.0d) / 100.0d;
        double pow = Math.pow(parseDouble + 1.0d, (double) this.f3778a);
        this.f3779b = ((this.f3780c * parseDouble) * pow) / (pow - 1.0d);
        new DecimalFormat("###.##");
        return "" + Math.round(this.f3779b);
    }

    public String getFixedEmiamount(String str, String str2, String str3, String str4) {
        this.f3780c = Double.parseDouble(str.replaceAll(",", ""));
        this.f3778a = Integer.parseInt(str2);
        double parseDouble = Double.parseDouble(str3);
        this.f3781d = (double) Math.round(Double.parseDouble(str4));
        this.f3779b = (double) (Math.round((double) (Math.round((this.f3780c * parseDouble) / 100.0d) / 12)) + Math.round((double) Math.round(this.f3780c / ((double) this.f3778a))));
        new DecimalFormat("###.##");
        return "" + Math.round(this.f3779b);
    }

    public String getTotalPayable() {
        Log.d("ProcessingFeesConstant", this.f3781d + "");
        return ((this.f3779b * ((double) this.f3778a)) + this.f3781d) + "";
    }

    public double getTotalPayabled() {
        return this.f3779b * ((double) this.f3778a);
    }

    public double getEmiamount(double d, int i, double d2, double d3) {
        this.f3778a = i;
        this.f3781d = d3;
        double d4 = (d2 / 12.0d) / 100.0d;
        double pow = Math.pow(d4 + 1.0d, (double) i);
        this.f3779b = ((d * d4) * pow) / (pow - 1.0d);
        new DecimalFormat("###.##");
        return this.f3779b;
    }

    public double getFixedEmiamount(double d, int i, double d2, double d3) {
        this.f3778a = i;
        this.f3781d = d3;
        double d4 = (((d2 * d) / 100.0d) / 12.0d) + (d / ((double) i));
        this.f3779b = d4;
        return d4;
    }
}


