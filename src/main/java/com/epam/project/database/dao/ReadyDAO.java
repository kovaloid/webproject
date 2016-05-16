package com.epam.project.database.dao;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.CarBean;

/**
 * Interface for Car Bean.
 * <p>The interface implements DAO interface and add one method.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public interface ReadyDAO<T> extends DAO<T> {
    /**
     * Method that uses 'SELECT ... WHERE READY=да' query to table 'Cars'.
     *
     * @return table object that contain table data
     */
    Table<CarBean> getAllReady();
}
