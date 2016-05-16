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
        assert pool != null;
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
            log.info("All records were selected in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return usersTable;
    }

    @Override
    public UserBean getByID(Integer id) {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        UserBean userBean = new UserBean();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, LOGIN, PASSWORD, ROLE FROM AUTOBASE.USERS " +
                    "WHERE='" + id + "' ORDER BY ID");
            List<UserBean> lines = parseTableLines(rs);
            if (lines.size() > 0) {
                userBean.setId(lines.get(0).getId());
                userBean.setLogin(lines.get(0).getLogin());
                userBean.setPassword(lines.get(0).getPassword());
                userBean.setRole(lines.get(0).getRole());
            }
            log.info("One record was selected in table [USERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return userBean;
    }

    @Override
    public void add(UserBean user) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.USERS(LOGIN,PASSWORD,ROLE) VALUES(?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
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
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.USERS SET LOGIN=?, PASSWORD=?, ROLE=? WHERE ID=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getId());
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
        assert pool != null;
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
