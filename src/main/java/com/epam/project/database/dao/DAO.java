package com.epam.project.database.dao;

import com.epam.project.beans.Table;

public interface DAO<T> {
    Table<T> getAll();

    T getByID(Integer id);

    void add(T line);

    void update(T line);

    void remove(T line);
}
