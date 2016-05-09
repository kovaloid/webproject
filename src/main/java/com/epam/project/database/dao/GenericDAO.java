package com.epam.project.database.dao;

import java.util.List;

public interface GenericDAO<T> {

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<T> getAll();

    /** Создает новую запись, соответствующую объекту object */
    void add(T object);

    /** Сохраняет состояние объекта в базе данных */
    void update(T object);

    /** Удаляет запись об объекте из базы данных */
    void remove(T object);

}
