package com.epam.project.database.dao;

import com.epam.project.beans.TableBean;

public interface ReadyDAO<T> extends DAO<T> {
    TableBean getAllReady();
}
