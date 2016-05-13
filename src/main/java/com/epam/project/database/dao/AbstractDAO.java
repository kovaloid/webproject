package com.epam.project.database.dao;

import com.epam.project.beans.TableBean;
import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected final static ConnectionPool pool = ConnectionPool.getInstance();
    protected final static Logger log = Logger.getRootLogger();

    @Override
    public abstract TableBean getAll();

    @Override
    public abstract void add(T object);

    @Override
    public abstract void update(T object);

    @Override
    public abstract void remove(T object);

    protected abstract List<T> parseTableLines(ResultSet rs);

    protected List<String> parseTableHeaders(ResultSet rs) {
        List<String> result = new LinkedList<>();
        try {
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                result.add(rs.getMetaData().getColumnName(i));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
