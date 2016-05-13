package com.epam.project.database.dao.autobase;

import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.DriverBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriversDAO extends AbstractDAO<DriverBean> {

    @Override
    public TableBean getAll() {
        Connection con = pool.takeConnection();
        TableBean<DriverBean> driversTable = new TableBean<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<DriverBean> lines = parseTableLines(rs);
            driversTable.setHeaders(headers);
            driversTable.setLines(lines);
            driversTable.setCountColumns(headers.size());
            driversTable.setCountLines(lines.size());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return driversTable;
    }

    @Override
    public void add(DriverBean driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO KOVAL.AUTO_PERSONNEL(FIRST_NAME,LAST_NAME) VALUES(?,?)");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
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
    public void update(DriverBean driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE KOVAL.AUTO_PERSONNEL SET FIRST_NAME=?, LAST_NAME=? WHERE ID=?");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
            stmt.setInt(3, driver.getId());
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
    public void remove(DriverBean driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID=?");
            stmt.setInt(1, driver.getId());
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
    protected List<DriverBean> parseTableLines(ResultSet rs) {
        List<DriverBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                DriverBean driver = new DriverBean();
                driver.setId(rs.getInt("ID"));
                driver.setName(rs.getString("FIRST_NAME"));
                driver.setSurname(rs.getString("LAST_NAME"));
                result.add(driver);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }

}
