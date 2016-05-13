package com.epam.project.database.dao;

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
