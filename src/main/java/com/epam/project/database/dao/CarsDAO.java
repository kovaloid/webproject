package com.epam.project.database.dao;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.database.beans.Car;
import org.apache.log4j.Logger;

import java.util.List;

public class CarsDAO implements GenericDAO<Car> {

    private ConnectionPool pool;
    private final static Logger log = Logger.getRootLogger();

    public CarsDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public void add(Car driver) {

    }

    @Override
    public void update(Car driver) {

    }

    @Override
    public void remove(Car driver) {

    }
}
