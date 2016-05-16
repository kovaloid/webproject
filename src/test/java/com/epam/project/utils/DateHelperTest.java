package com.epam.project.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateHelperTest {

    @Test
    public void getMonthString() throws Exception {
        assertEquals(DateHelper.getMonthString(1), "JAN");
        assertEquals(DateHelper.getMonthString(3), "MAR");
        assertEquals(DateHelper.getMonthString(5), "MAY");
    }

    @Test
    public void getMonthNumber() throws Exception {
        assertEquals(DateHelper.getMonthNumber("FEB"), 2);
        assertEquals(DateHelper.getMonthNumber("APR"), 4);
        assertEquals(DateHelper.getMonthNumber("JUN"), 6);
    }

    @Test
    public void convert() throws Exception {
        assertEquals(DateHelper.convert("01", "05", "1994").toString(), "1994-05-01");
        assertEquals(DateHelper.convert("01", "string", "1994").toString(), "1970-01-01");
    }
}