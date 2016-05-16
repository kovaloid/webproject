package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.DriverBean;
import com.epam.project.database.dao.DAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriversDAOTest {

    @Test
    public void getAll() throws Exception {
        DAO<DriverBean> driversDAO = new DriversDAO();
        Table<DriverBean> driversTable = driversDAO.getAll();
        assertEquals(driversTable.getCountColumns(), 5);
    }
}