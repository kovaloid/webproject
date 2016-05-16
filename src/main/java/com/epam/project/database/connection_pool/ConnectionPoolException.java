package com.epam.project.database.connection_pool;

/**
 * Connection Pool Exception.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
class ConnectionPoolException extends Exception {
    ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
