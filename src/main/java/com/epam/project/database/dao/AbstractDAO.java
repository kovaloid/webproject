package com.epam.project.database.dao;

import com.epam.project.beans.Table;
import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract DAO implements DAO and add two methods for work with tables.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
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

    /**
     * Method that gets ResultSet of table content and parse it to List.
     *
     * @param rs result set object
     * @return list collection of table lines
     */
    protected abstract List<T> parseTableLines(ResultSet rs);

    /**
     * Method that gets ResultSet of table headers and parse it to List.
     *
     * @param rs result set object
     * @return list collection of table headers
     */
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
