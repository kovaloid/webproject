package com.epam.project.database.dao;

import com.epam.project.beans.Table;
import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected final static ConnectionPool pool = ConnectionPool.getInstance();
    protected final static Logger log = Logger.getRootLogger();

    @Override
    public abstract Table<T> getAll();

    @Override
    public abstract void add(T line);

    @Override
    public abstract void update(T line);

    @Override
    public abstract void remove(T line);

    protected abstract List<T> parseTableLines(ResultSet rs);

    protected List<String> parseTableHeaders(ResultSet rs) {
        List<String> headers = new LinkedList<>();
        try {
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                headers.add(rs.getMetaData().getColumnName(i));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return headers;
    }
}
