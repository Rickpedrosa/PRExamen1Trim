package com.example.prexamen1trim.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ValueUtils {

    private ValueUtils() {

    }

    public static String getFormattedFloat(String string) {
        return string.replace(",", ".");
    }

    public static String getDateCurrentTimeZone(long timestamp) {
        try {
            if (timestamp != 0) {
                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getDefault();
                calendar.setTimeInMillis(timestamp * 1000);
                calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date currenTimeZone = calendar.getTime();
                return sdf.format(currenTimeZone);
            } else {
                return "-";
            }

        } catch (Exception ignored) {
        }
        return "";
    }
}
