package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.UserBean;
import com.epam.project.database.dao.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO extends AbstractDAO<UserBean> {

    @Override
    public Table<UserBean> getAll() {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<UserBean> usersTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, LOGIN, PASSWORD, ROLE FROM AUTOBASE.USERS ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<UserBean> lines = parseTableLines(rs);
            usersTable.setHeaders(headers);
            usersTable.setLines(lines);
            usersTable.setCountColumns(headers.size());
            usersTable.setCountLines(lines.size());
            log.info("All records was selected in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return usersTable;
    }

    @Override
    public void add(UserBean user) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.USERS(LOGIN,PASSWORD) VALUES(?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more or less than 1 record: " + rows);
            }
            log.info("One record was inserted in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(UserBean user) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.USERS SET PASSWORD=? WHERE ID=?");
            stmt.setString(1, user.getPassword());
            stmt.setInt(2, user.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more or less than 1 record: " + rows);
            }
            log.info("One record was updated in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(UserBean user) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM AUTOBASE.USERS WHERE ID=?");
            stmt.setInt(1, user.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more or less than 1 record: " + rows);
            }
            log.info("One record was deleted in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    protected List<UserBean> parseTableLines(ResultSet rs) {
        List<UserBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt("ID"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRole(rs.getString("ROLE"));
                result.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
