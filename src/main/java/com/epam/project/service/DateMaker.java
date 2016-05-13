package com.epam.project.service;

import org.apache.log4j.Logger;

import java.sql.Date;


public class DateMaker {

    private static final Logger log = Logger.getRootLogger();

    private static String getMonthString(int number) {
        String month;
        switch (number) {
            case 1:
                month = "JAN";
                break;
            case 2:
                month = "FEB";
                break;
            case 3:
                month = "MAR";
                break;
            case 4:
                month = "APR";
                break;
            case 5:
                month = "MAY";
                break;
            case 6:
                month = "JUN";
                break;
            case 7:
                month = "JUL";
                break;
            case 8:
                month = "AUG";
                break;
            case 9:
                month = "SEP";
                break;
            case 10:
                month = "OCT";
                break;
            case 11:
                month = "NOV";
                break;
            case 12:
                month = "DEC";
                break;
            default:
                month = "none";
        }
        return month;
    }

    private static String getMonthNumber(String str) {
        String month;
        switch (str) {
            case "JAN":
                month = "01";
                break;
            case "FEB":
                month = "02";
                break;
            case "MAR":
                month = "03";
                break;
            case "APR":
                month = "04";
                break;
            case "MAY":
                month = "05";
                break;
            case "JUN":
                month = "06";
                break;
            case "JUL":
                month = "07";
                break;
            case "AUG":
                month = "08";
                break;
            case "SEP":
                month = "09";
                break;
            case "OCT":
                month = "10";
                break;
            case "NOV":
                month = "11";
                break;
            case "DEC":
                month = "12";
                break;
            default:
                month = "none";
        }
        return month;
    }

    //public static String make(String day, String month, String year) {
    //    return day + "-" + month + "-" + year;
    //}

    public static Date make(String day, String month, String year) {
        String date = year + "-" + getMonthNumber(month) + "-" + day;
        Date d = new Date(0);
        try {
            log.info(date);
            d = Date.valueOf(date);

        } catch (IllegalArgumentException e) {
            log.error(date);

        }
        return d;
    }
}
