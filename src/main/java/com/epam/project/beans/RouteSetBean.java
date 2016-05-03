package com.epam.project.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteSetBean {

    private ResultSet rs;
    private List<String> listIDs;
    private List<String> listRoutes;

    public RouteSetBean(ResultSet rs) {
        this.rs = rs;
        listIDs = new ArrayList<>();
        listRoutes = new ArrayList<>();
        fill();
    }

    public RouteSetBean() {
    }

    private void fill() {
        try {
            while (rs.next()) {
                listIDs.add(rs.getString(1));
                listRoutes.add(rs.getString(2));
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
        return listRoutes.toArray(new String[getSize()]);
    }
}
