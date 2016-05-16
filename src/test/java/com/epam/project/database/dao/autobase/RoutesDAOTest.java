package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoutesDAOTest {

    @Test
    public void getAll() throws Exception {
        DAO<RouteBean> routesDAO = new RoutesDAO();
        Table<RouteBean> routesTable = routesDAO.getAll();
        assertEquals(routesTable.getCountColumns(), 4);
    }
}