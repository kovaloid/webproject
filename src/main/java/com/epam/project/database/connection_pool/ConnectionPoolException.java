package com.epam.project.database.connection_pool;

class ConnectionPoolException extends Exception {
    ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
