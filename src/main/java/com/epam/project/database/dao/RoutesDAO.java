package com.epam.project.database.dao;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.database.beans.Route;
import org.apache.log4j.Logger;

import java.util.List;

public class RoutesDAO implements GenericDAO<Route> {

    private ConnectionPool pool;
    private final static Logger log = Logger.getRootLogger();

    public RoutesDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public List<Route> getAll() {
        return null;
    }

    @Override
    public void add(Route driver) {

    }

    @Override
    public void update(Route driver) {

    }

    @Override
    public void remove(Route driver) {

    }
}
