package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO class to access the data about 'Routes' in database.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class RoutesDAO extends AbstractDAO<RouteBean> {

    @Override
    public Table<RouteBean> getAll() {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<RouteBean> routesTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, ROUTE_NAME, LENGTH, PRICE FROM AUTOBASE.ROUTES ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<RouteBean> lines = parseTableLines(rs);
            routesTable.setHeaders(headers);
            routesTable.setLines(lines);
            routesTable.setCountColumns(headers.size());
            routesTable.setCountLines(lines.size());
            log.info("All records were selected in table [ROUTES]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return routesTable;
    }

    @Override
    public RouteBean getByID(Integer id) {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        RouteBean routeBean = new RouteBean();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, ROUTE_NAME, LENGTH, PRICE FROM AUTOBASE.ROUTES " +
                    "WHERE ID = '" + id + "' ORDER BY ID");
            List<RouteBean> lines = parseTableLines(rs);
            if (lines.size() > 0) {
                routeBean.setId(lines.get(0).getId());
                routeBean.setRouteName(lines.get(0).getRouteName());
                routeBean.setLength(lines.get(0).getLength());
                routeBean.setPrice(lines.get(0).getPrice());
            }
            log.info("One record was selected in table [ROUTES]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return routeBean;
    }

    @Override
    public void add(RouteBean route) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.ROUTES(ROUTE_NAME, LENGTH, PRICE) VALUES(?,?,?)");
            stmt.setString(1, route.getRouteName());
            stmt.setInt(2, route.getLength());
            stmt.setInt(3, route.getPrice());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more or less than 1 record: " + rows);
            }
            log.info("One record was inserted in table [ROUTES]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(RouteBean route) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.ROUTES SET ROUTE_NAME=?, LENGTH=?, PRICE=? WHERE ID=?");
            stmt.setString(1, route.getRouteName());
            stmt.setInt(2, route.getLength());
            stmt.setInt(3, route.getPrice());
            stmt.setInt(4, route.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more or less than 1 record: " + rows);
            }
            log.info("One record was updated in table [ROUTES]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(RouteBean route) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM AUTOBASE.ROUTES WHERE ID=?");
            stmt.setInt(1, route.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more or less than 1 record: " + rows);
            }
            log.info("One record was deleted in table [ROUTES]");
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
                route.setRouteName(rs.getString("ROUTE_NAME"));
                route.setLength(rs.getInt("LENGTH"));
                route.setPrice(rs.getInt("PRICE"));
                result.add(route);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}

