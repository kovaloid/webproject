package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.DAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class JournalDAOTest {

    @Test
    public void getAll() throws Exception {
        DAO<JournalBean> journalDAO = new JournalDAO();
        Table<JournalBean> journalTable = journalDAO.getAll();
        assertEquals(journalTable.getCountColumns(), 6);
    }
}