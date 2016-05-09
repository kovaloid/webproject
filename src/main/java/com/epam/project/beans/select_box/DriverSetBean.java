package com.epam.project.beans.select_box;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverSetBean {

    private ResultSet rs;
    private List<String> listIDs;
    private List<String> listNames;

    public DriverSetBean(ResultSet rs) {
        this.rs = rs;
        listIDs = new ArrayList<>();
        listNames = new ArrayList<>();
        fill();
    }

    public DriverSetBean() {
    }

    private void fill() {
        try {
            while (rs.next()) {
                listIDs.add(rs.getString(1));
                listNames.add(rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return listIDs.size() - 1;
    }

    public String[] getListIDs() {
        return listIDs.toArray(new String[getSize()]);
    }

    public String[] getListNames() {
        return listNames.toArray(new String[getSize()]);
    }
}