package com.gm.emi_calculator.Constant;

public class Constant_NumToWord_Rupee {
    private static String input;
    private static String[] maxs = {"", "", " Hundred", " Thousand", " Lakh", " Crore", " Arab"};
    private static String[] teen = {" Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};
    private static String[] tens = {" Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
    private static String[] units = {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"};

    public static String convertNumberToWords(long j) {
        input = numToString(j);
        String str = "";
        int i = 1;
        boolean z = false;
        while (input.length() > 0) {
            try {
                if (i == 1) {
                    if (input.length() >= 2) {
                        String str2 = input;
                        String substring = str2.substring(str2.length() - 2);
                        String str3 = input;
                        input = str3.substring(0, str3.length() - 2);
                        str = str + digits(substring);
                    } else if (input.length() == 1) {
                        str = str + digits(input);
                        input = "";
                    }
                } else if (i == 2) {
                    String str4 = input;
                    String substring2 = str4.substring(str4.length() - 1);
                    String str5 = input;
                    input = str5.substring(0, str5.length() - 1);
                    if (str.length() > 0 && digits(substring2) != "") {
                        str = digits(substring2) + maxs[i] + " and" + str;
                    } else if (digits(substring2) != "") {
                        str = digits(substring2) + maxs[i] + str;
                    }
                    i++;
                    z = true;
                } else if (i > 2) {
                    if (input.length() >= 2) {
                        String str6 = input;
                        String substring3 = str6.substring(str6.length() - 2);
                        String str7 = input;
                        input = str7.substring(0, str7.length() - 2);
                        if (!z && str.length() > 0) {
                            str = digits(substring3) + maxs[i] + " and" + str;
                        } else if (digits(substring3) != "") {
                            str = digits(substring3) + maxs[i] + str;
                        }
                    } else if (input.length() == 1) {
                        if (z || str.length() <= 0) {
                            if (digits(input) != "") {
                                str = digits(input) + maxs[i] + str;
                            }
                            input = "";
                        } else {
                            str = digits(input) + maxs[i] + " and" + str;
                        }
                    }
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    private static String digits(String str) {
        String str2 = "";
        for (int length = str.length() - 1; length >= 0; length--) {
            int charAt = str.charAt(length) - '0';
            if (length == 0 && charAt > 1 && str.length() > 1) {
                str2 = tens[charAt - 2] + str2;
            } else if (length == 0 && charAt == 1 && str.length() == 2) {
                int i = 0;
                for (int i2 = 0; i2 < 2; i2++) {
                    i = (i * 10) + (str.charAt(i2) - '0');
                }
                return teen[i - 10];
            } else if (charAt > 0) {
                str2 = units[charAt] + str2;
            }
        }
        return str2;
    }

    private static String numToString(long j) {
        String str = "";
        while (j != 0) {
            str = ((char) ((int) ((j % 10) + 48))) + str;
            j /= 10;
        }
        return str;
    }
}

