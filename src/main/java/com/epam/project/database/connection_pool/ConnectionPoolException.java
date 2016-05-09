package com.epam.project.database.connection_pool;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
