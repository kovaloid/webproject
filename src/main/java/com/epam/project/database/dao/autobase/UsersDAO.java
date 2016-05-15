package com.epam.project.database.dao.autobase;

import com.epam.project.beans.TableBean;
import com.epam.project.beans.UserBean;
import com.epam.project.beans.lines.CarBean;
import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.AuthDAO;
import com.epam.project.database.dao.DAOException;
import com.epam.project.database.dao.ReadyDAO;
import com.epam.project.service.constants.Account;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO extends AbstractDAO<UserBean> implements AuthDAO<UserBean> {
    @Override
    public TableBean getAll() {
        Connection con = pool.takeConnection();
        TableBean<UserBean> usersTable = new TableBean<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, LOGIN, PASSWORD, ROLE FROM AUTOBASE.USERS ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<UserBean> lines = parseTableLines(rs);
            usersTable.setHeaders(headers);
            usersTable.setLines(lines);
            usersTable.setCountColumns(headers.size());
            usersTable.setCountLines(lines.size());
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
            log.info(rows + " row(s) was inserted");
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
            log.info(rows + " row(s) was updated");
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
            log.info(rows + " row(s) was deleted");
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

    @Override
    public String checkUser(UserBean user) {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT LOGIN, PASSWORD FROM AUTOBASE.USERS");
            while (rs.next()) {
                String current_login = rs.getString(1).trim();
                String current_password = rs.getString(2).trim();
                if (current_login.equalsIgnoreCase(user.getLogin())) {
                    if (current_password.equals(user.getPassword())) {
                        return Account.Result.SUCCESS;
                    } else {
                        return Account.Result.FAIL_PASSWORD;
                    }
                } else {
                    result = Account.Result.FAIL_LOGIN;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return result;
    }

    @Override
    public String defineRole(UserBean user) {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ROLE FROM AUTOBASE.USERS WHERE LOGIN='" + user.getLogin() + "'");
            while (rs.next()) {
                String role = rs.getString(1);
                if (role == null) {
                    return Account.Role.CLIENT;
                } else if (role.trim().equalsIgnoreCase(Account.Role.ADMIN)) {
                    return Account.Role.ADMIN;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return Account.Role.CLIENT;
    }

    @Override
    public boolean checkSameLogin(UserBean user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT LOGIN FROM AUTOBASE.USERS");
            while (rs.next()) {
                String current_login = rs.getString(1);
                if ((current_login.trim().equalsIgnoreCase(user.getLogin()))) {
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
}
