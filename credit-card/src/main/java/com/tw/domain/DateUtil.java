package com.tw.domain;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
    public static Date addMonth(int month, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return new Date(calendar.getTime().getTime());
    }

    public static Date addDay(int day, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new Date(calendar.getTime().getTime());
    }
}
