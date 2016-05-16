package com.epam.project.database.connection_pool;

/**
 * Interface for constants from 'db.properties' file.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
interface DBParameter {
    String DB_DRIVER = "db.driver";
    String DB_URL = "db.url";
    String DB_USER = "db.user";
    String DB_PASSWORD = "db.password";
    String DB_POOL_SIZE = "db.pool_size";
}
