package com.gm.emi_calculator.Constant;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Constant_CurrencyFormat {
    public static String format(double d) {
        if (d < 1000.0d) {
            return format("###", Double.valueOf(d));
        }
        return format(",##", Integer.valueOf((int) (d / 1000.0d))) + ',' + format("000", Double.valueOf(d % 1000.0d));
    }

    public static String rupeeFormat(String str) {
        String str2 = "";
        String replaceAll = str.replaceAll(",", str2);
        char charAt = replaceAll.charAt(replaceAll.length() - 1);
        int i = 0;
        for (int length = (replaceAll.length() - 1) - 1; length >= 0; length--) {
            str2 = replaceAll.charAt(length) + str2;
            i++;
            if (i % 2 == 0 && length > 0) {
                str2 = "," + str2;
            }
        }
        return str2 + charAt;
    }

    public String CurrencyFormattedvalue(double d) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(d);
    }

    private static String format(String str, Object obj) {
        return new DecimalFormat(str).format(obj);
    }
}
