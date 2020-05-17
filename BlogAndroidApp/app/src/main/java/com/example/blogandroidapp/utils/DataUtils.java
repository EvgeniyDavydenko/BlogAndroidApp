package com.example.blogandroidapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String ConvertTimeStampToDate(String inputTimeStamp){
        long timeStamp = Long.parseLong(inputTimeStamp);
        Date date = new Date(timeStamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }
}
