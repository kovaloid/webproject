package com.epam.project.database.dao;

import com.epam.project.beans.TableBean;

public interface DAO<T> {
    TableBean getAll();

    void add(T object);

    void update(T object);

    void remove(T object);
}
