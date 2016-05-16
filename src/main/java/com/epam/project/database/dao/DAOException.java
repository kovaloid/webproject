package com.epam.project.database.dao;

/**
 * DAO Exception.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class DAOException extends RuntimeException {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
