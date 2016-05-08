package com.epam.project.service;

import com.epam.project.database.ConnectManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 803337 on 08.05.2016.
 */
public class AccountManager {

    private boolean isValidUsername(String username) {
        return true;
    }

    private boolean isValidPassword(String username) {
        return true;
    }

    private boolean isValidRepeat(String username) {
        return true;
    }

    public String signup(String username, String password, String repeat) {

        boolean r_1 = isValidUsername(username);
        boolean r_2 = isValidPassword(password);
        boolean r_3 = isValidRepeat(repeat);

        return "success";
    }

    public String authenticate(String username, String password) {
        Connection con = ConnectManager.getNewConnection();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT LOGIN, PASSWORD FROM KOVAL.USERS");

            while(rs.next()){
                String current_login = rs.getString(1);
                String current_password = rs.getString(1);
                if ((current_login.trim().equalsIgnoreCase(username.trim()))
                        && (current_password.trim().equals(password.trim()))) {
                    return "success";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "failure";
    }

}
