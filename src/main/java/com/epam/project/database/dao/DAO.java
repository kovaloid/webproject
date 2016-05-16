package com.epam.project.database.dao;

import com.epam.project.beans.Table;

/**
 * Interface that represents one Data Access Object.
 * <p>The interface provides methods by which you can get, add, update
 * or remove data from tables in database.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public interface DAO<T> {
    /**
     * Method that uses 'SELECT' query to database.
     *
     * @return table object that contain all table data
     */
    Table<T> getAll();

    /**
     * Method that uses 'SELECT ... WHERE ID=id' query to database.
     *
     * @param id sequence number of line in database
     * @return bean object that contain data about line with this ID
     */
    T getByID(Integer id);

    /**
     * Method that uses 'INSERT' query to database.
     *
     * @param line object that implements Line interface
     */
    void add(T line);

    /**
     * Method that uses 'UPDATE' query to database.
     *
     * @param line object that implements Line interface
     */
    void update(T line);

    /**
     * Method that uses 'DELETE' query to database.
     *
     * @param line object that implements Line interface
     */
    void remove(T line);
}
