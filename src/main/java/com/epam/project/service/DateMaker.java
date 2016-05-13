package com.epam.project.service;

import java.sql.Date;

public class DateMaker {

    private static String getMonthString(int number) {
        String month;
        switch(number) {
            case 1: month = "JAN";
                break;
            case 2: month = "FEB";
                break;
            case 3: month = "MAR";
                break;
            case 4: month = "APR";
                break;
            case 5: month = "MAY";
                break;
            case 6: month = "JUN";
                break;
            case 7: month = "JUL";
                break;
            case 8: month = "AUG";
                break;
            case 9: month = "SEP";
                break;
            case 10: month = "OCT";
                break;
            case 11: month = "NOV";
                break;
            case 12: month = "DEC";
                break;
            default: month = "none";
        }
        return month;
    }

    //public static String make(String day, String month, String year) {
    //    return day + "-" + month + "-" + year;
    //}

    public static Date make(String day, String month, String year) {
        String date = year + "-" + month + "-" + day;
        return Date.valueOf(date);
    }
}
