package com.epam.project.database.dao;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.CarBean;

public interface ReadyDAO<T> extends DAO<T> {
    Table<CarBean> getAllReady();
}
