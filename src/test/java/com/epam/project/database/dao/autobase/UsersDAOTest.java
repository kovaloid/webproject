package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.UserBean;
import com.epam.project.database.dao.DAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersDAOTest {

    @Test
    public void getAll() throws Exception {
        DAO<UserBean> usersDAO = new UsersDAO();
        Table<UserBean> usersTable = usersDAO.getAll();
        assertEquals(usersTable.getCountColumns(), 4);
    }
}