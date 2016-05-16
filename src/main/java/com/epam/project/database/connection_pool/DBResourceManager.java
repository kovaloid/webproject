package com.epam.project.database.connection_pool;

import java.util.ResourceBundle;

/**
 * Class is used to access the 'db.properties' file and get values from it.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    static DBResourceManager getInstance() {
        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
