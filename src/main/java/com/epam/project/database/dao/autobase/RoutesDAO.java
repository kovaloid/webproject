package com.epam.project.database.dao.autobase;

import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RoutesDAO extends AbstractDAO<RouteBean> {

    @Override
    public TableBean getAll() {
        Connection con = pool.takeConnection();
        TableBean<RouteBean> routesTable = new TableBean<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, NAME FROM KOVAL.ROUTES ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<RouteBean> lines = parseTableLines(rs);
            routesTable.setHeaders(headers);
            routesTable.setLines(lines);
            routesTable.setCountColumns(headers.size());
            routesTable.setCountLines(lines.size());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return routesTable;
    }

    @Override
    public void add(RouteBean route) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO KOVAL.ROUTES(NAME) VALUES(?)");
            stmt.setString(1, route.getName());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more then 1 record: " + rows);
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
    public void update(RouteBean route) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE KOVAL.ROUTES SET NAME=? WHERE ID=?");
            stmt.setString(1, route.getName());
            stmt.setInt(2, route.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more then 1 record: " + rows);
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
    public void remove(RouteBean route) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM KOVAL.ROUTES WHERE ID=?");
            stmt.setInt(1, route.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more then 1 record: " + rows);
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
    protected List<RouteBean> parseTableLines(ResultSet rs) {
        List<RouteBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                RouteBean route = new RouteBean();
                route.setId(rs.getInt("ID"));
                route.setName(rs.getString("NAME"));
                result.add(route);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }

}

