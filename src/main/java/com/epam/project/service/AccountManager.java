package com.epam.project.service;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.service.constants.Account;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManager {

    private final static Logger log = Logger.getRootLogger();
    
    private boolean checkSameUsername(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT LOGIN FROM KOVAL.USERS");
            while (rs.next()) {
                String current_login = rs.getString(1);
                if ((current_login.trim().equalsIgnoreCase(username.trim()))) {
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return true;
    }

    private boolean isBadUsername(String username) {
        boolean condition_1 = (username.length() < 20) && (username.length() > 3);
        boolean condition_2 = checkSameUsername(username);
        // Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква)
        //boolean condition_3 = username.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
        //return !(condition_1 && condition_2 && condition_3);
        return !(condition_1 && condition_2);
    }

    private boolean isBadPassword(String password) {
        boolean condition_1 = (password.length() < 20) && (password.length() > 3);
        //Пароль (Строчные и прописные латинские буквы, цифры):
        //boolean condition_2 = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
        //return !(condition_1 && condition_2);
        return !(condition_1);
    }

    private boolean isBadRepeat(String password, String repeat) {
        return !password.equals(repeat);
    }

    public String signup(String username, String password, String repeat) {
        boolean bad_username = isBadUsername(username);
        boolean bad_password = isBadPassword(password);
        boolean bad_repeat = isBadRepeat(password, repeat);
        if (bad_username || bad_password || bad_repeat) {
            if (bad_username) return Account.Result.FAIL_USERNAME;
            if (bad_password) return Account.Result.FAIL_PASSWORD;
            if (bad_repeat) return Account.Result.FAIL_REPEAT;
        }
        return Account.Result.SUCCESS;
    }

    public String authenticate(String username, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT LOGIN, PASSWORD FROM KOVAL.USERS");
            while (rs.next()) {
                String current_login = rs.getString(1);
                String current_password = rs.getString(1);
                if (current_login.trim().equalsIgnoreCase(username.trim())) {
                    if (current_password.trim().equals(password.trim()))
                        return Account.Result.SUCCESS;
                    else
                        return Account.Result.FAIL_PASSWORD;
                } else return Account.Result.FAIL_USERNAME;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return Account.Result.FAIL_OTHER;
    }

    public String defineRole(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ROLE FROM KOVAL.USERS WHERE LOGIN='" + username + "'");
            while (rs.next()) {
                String role = rs.getString(1);
                if (role == null)
                    return Account.Role.CLIENT;
                else if (role.trim().equalsIgnoreCase(Account.Role.ADMIN))
                    return Account.Role.ADMIN;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return Account.Role.CLIENT;
    }
}
