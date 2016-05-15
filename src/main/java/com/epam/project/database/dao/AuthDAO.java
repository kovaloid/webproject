package com.epam.project.database.dao;

public interface AuthDAO<T> extends DAO<T> {
    String checkUser(T user);

    String defineRole(T user);

    boolean checkSameLogin(T user);
}
