package com.epam.project.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetBean {

    private ResultSet rs;

    public ResultSetBean(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSetBean() {
    }

    public boolean getNext() {
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getColumnCount() {
        try {
            return rs.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int i) {
        try {
            return rs.getMetaData().getColumnName(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public String[] getLine() {
        String[] line = new String[3];
        try {
            line[0] = rs.getString(1);
            line[1] = rs.getString(2);
            line[2] = rs.getString(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }

}
