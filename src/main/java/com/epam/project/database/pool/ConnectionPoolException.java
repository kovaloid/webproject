package com.epam.project.database.pool;

class ConnectionPoolException extends Exception {
    ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
