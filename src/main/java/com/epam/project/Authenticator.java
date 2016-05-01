package com.epam.project;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authenticator {

    private Connection getConnection() {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public String authenticate(String username, String password) {

        Statement stmt;
        ResultSet rs;
        try {
            stmt = getConnection().createStatement();
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
