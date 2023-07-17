package com.model;

import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constants {
    public static Logger log;
    public static String checkIn = "d";
    public final static String RC = "rc";
    public final static String RM = "rm";
    public final static String success = "0";
    public final static String already = "1001";
    public final static String data = "data";
    public final static String accessToken = "accessToken";
    public final static String in = "in";
    public final static String out = "out";

    public static String getNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(System.currentTimeMillis());
        return time;
    }
    public static String getYearDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(System.currentTimeMillis());
        return time;
    }
    public static int getYear(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int dateYear = calendar.get(Calendar.YEAR);
        return dateYear;
    }

    public static void main(String[] args) {
        String yearDate = getYearDate();
        System.out.println(yearDate);
    }
}
