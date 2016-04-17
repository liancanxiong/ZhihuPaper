package com.brilliantbear.zhihupaper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cx.lian on 2016/4/14.
 */
public class DateUtils {
    public static String parseStandardDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }

    public static String parseMilliseconds(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }

    public static String parseStandardDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }

    public static String parseMilliseconds(long milliseconds, String format) {
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }

    public static Date parseStandardString(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseStandardString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
