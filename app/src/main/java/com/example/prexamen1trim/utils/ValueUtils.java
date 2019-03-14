package com.example.prexamen1trim.utils;

public class ValueUtils {

    private ValueUtils() {

    }

    public static String getFormattedFloat(String string) {
        return string.replace(",", ".");
    }
}
