package com.epam.project.util;

import java.util.ArrayList;
import java.util.List;

public class MonthDefiner {
    private static List<String> months = new ArrayList<>();

    static {
        months.add("01");
        months.add("02");
        months.add("03");
        months.add("04");
        months.add("05");
        months.add("06");
        months.add("07");
        months.add("08");
        months.add("09");
        months.add("10");
        months.add("11");
        months.add("12");
    }

    public static List<String> getMonthsList() {
        return months;
    }
}
