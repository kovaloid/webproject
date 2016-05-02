package com.epam.project.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class ResultSetBean {
    private ResultSet set;

    public ResultSetBean(ResultSet set) {
        this.set = set;
    }

    public ResultSetBean() {
    }

    public String getSize() {
        try {
            return Integer.toString(set.getFetchSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String getElement() {
        try {
            set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
