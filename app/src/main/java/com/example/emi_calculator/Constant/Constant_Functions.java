package com.example.emi_calculator.Constant;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.appcompat.app.AlertDialog;

import com.github.mikephil.charting.utils.Utils;

import java.util.Calendar;

public class Constant_Functions {

    /* renamed from: a */
    double f3258a;

    /* renamed from: b */
    double f3259b;

    /* renamed from: c */
    double f3260c;

    /* renamed from: d */
    double f3261d;

    /* renamed from: e */
    double f3262e;

    /* renamed from: f */
    double f3263f;

    /* renamed from: g */
    int f3264g;

    /* renamed from: h */
    int f3265h;

    /* renamed from: i */
    int f3266i;

    /* renamed from: j */
    double f3267j;

    /* renamed from: k */
    double f3268k;

    /* renamed from: l */
    double f3269l;

    /* renamed from: m */
    double f3270m = Utils.DOUBLE_EPSILON;

    /* renamed from: n */
    double f3271n = Utils.DOUBLE_EPSILON;

    /* renamed from: o */
    Calendar f3272o = Calendar.getInstance();



    public Constant_Functions(Activity activity) {
    }



    public static String getPercentage(double d, double d2) {
        return String.format("%.2f", new Object[]{Double.valueOf((d2 * 100.0d) / d)}) + "%";
    }


}
