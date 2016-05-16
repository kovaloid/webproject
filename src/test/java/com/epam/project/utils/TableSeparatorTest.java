package com.epam.project.utils;

import com.epam.project.beans.Table;
import com.epam.project.beans.UserBean;
import com.epam.project.beans.lines.Line;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.UsersDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableSeparatorTest {

    @Test
    public void getTableNumber() throws Exception {
        assertEquals(TableSeparator.getTableNumber("-50", 20), 1);
        assertEquals(TableSeparator.getTableNumber("-1", 20), 1);
        assertEquals(TableSeparator.getTableNumber("0", 20), 1);
        assertEquals(TableSeparator.getTableNumber("1", 20), 1);
        assertEquals(TableSeparator.getTableNumber("2", 20), 2);
        assertEquals(TableSeparator.getTableNumber("25", 20), 1);
        assertEquals(TableSeparator.getTableNumber("5", 20), 5);
        assertEquals(TableSeparator.getTableNumber("10", 20), 10);
        assertEquals(TableSeparator.getTableNumber("100", 20), 1);
        assertEquals(TableSeparator.getTableNumber("string", 20), 1);
    }

    @Test
    public void separate() throws Exception {
        DAO<UserBean> usersDAO = new UsersDAO();
        Table<UserBean> usersTable = usersDAO.getAll();
        Table<Line>[] usersTablesArray = TableSeparator.separate(usersTable);
        for (int i = 1; i < usersTablesArray[0].getCountColumns(); i++) {
            assertEquals(usersTablesArray[0].getLines().get(0).getColumn(i), Account.Role.ADMIN);
        }
    }
}