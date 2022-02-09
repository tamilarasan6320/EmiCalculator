package com.example.emi_calculator.Constant;

import java.text.NumberFormat;
import java.util.Locale;

public class Constant_CurrencyFormatDoller {
    public static String dollerFormat(String str, String str2) {
        NumberFormat numberFormat;
        if (str2.equalsIgnoreCase("₹")) {
            numberFormat = NumberFormat.getNumberInstance(new Locale("en", "IN"));
        } else {
            numberFormat = NumberFormat.getNumberInstance(Locale.US);
        }
        String format = numberFormat.format(Double.parseDouble(str.replaceAll(",", "").replaceAll("\\$", "").replaceAll("\\u20B9", "").trim()));
        if (format.contains(".")) {
            format = format.split("\\.")[0];
        }
        return format.replaceAll("$", "").replaceAll("₹", "").trim();
    }

    public static String formatDollar(double d) {
        if (d < 1000.0d) {
            return String.format("###", new Object[]{Double.valueOf(d)});
        }
        return String.format(",###", new Object[]{Integer.valueOf((int) (d / 1000.0d))}) + ',' + String.format("000", new Object[]{Double.valueOf(d % 1000.0d)});
    }

    public String CurrencyFormattedvalue(double d) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(d);
    }
}

