package com.epam.project.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarSetBean {

    private ResultSet rs;
    private List<String> listIDs;
    private List<String> listCars;

    public CarSetBean(ResultSet rs) {
        this.rs = rs;
        listIDs = new ArrayList<>();
        listCars = new ArrayList<>();
        fill();
    }

    public CarSetBean() {
    }

    private void fill() {
        try {
            while (rs.next()) {
                listIDs.add(rs.getString(1));
                listCars.add(rs.getString(2) + "_" + rs.getString(3));
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
        return listCars.toArray(new String[getSize()]);
    }
}
