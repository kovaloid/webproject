package com.epam.project.utils;

import org.apache.log4j.Logger;

import java.sql.Date;

/**
 * This util class help to convert from String to Date.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class DateHelper {
    private static final Logger log = Logger.getRootLogger();

    @SuppressWarnings("unused")
    public static String getMonthString(int monthNumber) {
        String monthString;
        switch (monthNumber) {
            case 1:
                monthString = "JAN";
                break;
            case 2:
                monthString = "FEB";
                break;
            case 3:
                monthString = "MAR";
                break;
            case 4:
                monthString = "APR";
                break;
            case 5:
                monthString = "MAY";
                break;
            case 6:
                monthString = "JUN";
                break;
            case 7:
                monthString = "JUL";
                break;
            case 8:
                monthString = "AUG";
                break;
            case 9:
                monthString = "SEP";
                break;
            case 10:
                monthString = "OCT";
                break;
            case 11:
                monthString = "NOV";
                break;
            case 12:
                monthString = "DEC";
                break;
            default:
                monthString = "none";
        }
        return monthString;
    }

    @SuppressWarnings("unused")
    public static int getMonthNumber(String monthString) {
        int monthNumber;
        switch (monthString) {
            case "JAN":
                monthNumber = 1;
                break;
            case "FEB":
                monthNumber = 2;
                break;
            case "MAR":
                monthNumber = 3;
                break;
            case "APR":
                monthNumber = 4;
                break;
            case "MAY":
                monthNumber = 5;
                break;
            case "JUN":
                monthNumber = 6;
                break;
            case "JUL":
                monthNumber = 7;
                break;
            case "AUG":
                monthNumber = 8;
                break;
            case "SEP":
                monthNumber = 9;
                break;
            case "OCT":
                monthNumber = 10;
                break;
            case "NOV":
                monthNumber = 11;
                break;
            case "DEC":
                monthNumber = 12;
                break;
            default:
                monthNumber = -1;
        }
        return monthNumber;
    }

    /**
     * This method create Date from three Strings of day, month and year.
     *
     * @param day string number of day
     * @param month string number of month
     * @param year string number of year
     * @return 1.0
     */
    public static Date convert(String day, String month, String year) {
        String dateString = year + "-" + month + "-" + day;
        Date date;
        try {
            date = Date.valueOf(dateString);
        } catch (IllegalArgumentException e) {
            log.error("Can't convert date from string");
            date = new Date(0);
        }
        return date;
    }
}
