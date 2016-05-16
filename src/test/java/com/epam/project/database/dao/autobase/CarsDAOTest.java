package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.CarBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.ReadyDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarsDAOTest {

    @Test
    public void getAll() throws Exception {
        DAO<CarBean> carsDAO = new CarsDAO();
        Table<CarBean> carsTable = carsDAO.getAll();
        assertEquals(carsTable.getCountColumns(), 7);
    }

    @Test
    public void getAllReady() throws Exception {
        ReadyDAO<CarBean> carsDAO = new CarsDAO();
        Table<CarBean> carsTable = carsDAO.getAllReady();
        assertEquals(carsTable.getCountColumns(), 2);
    }
}