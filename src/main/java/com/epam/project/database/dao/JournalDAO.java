package com.epam.project.database.dao;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.database.beans.Journal;
import org.apache.log4j.Logger;

import java.util.List;

public class JournalDAO implements GenericDAO<Journal> {

    private ConnectionPool pool;
    private final static Logger log = Logger.getRootLogger();

    public JournalDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public List<Journal> getAll() {
        return null;
    }

    @Override
    public void add(Journal driver) {

    }

    @Override
    public void update(Journal driver) {

    }

    @Override
    public void remove(Journal driver) {

    }
}
