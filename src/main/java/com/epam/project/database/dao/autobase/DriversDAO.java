package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.DriverBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO class to access the data about 'Drivers' in database.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class DriversDAO extends AbstractDAO<DriverBean> {

    @Override
    public Table<DriverBean> getAll() {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<DriverBean> driversTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, NAME, SURNAME, GENDER, PHONE FROM AUTOBASE.DRIVERS ORDER BY ID");
            List<String> headers = parseTableHeaders(rs);
            List<DriverBean> lines = parseTableLines(rs);
            driversTable.setHeaders(headers);
            driversTable.setLines(lines);
            driversTable.setCountColumns(headers.size());
            driversTable.setCountLines(lines.size());
            log.info("All records were selected in table [DRIVERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return driversTable;
    }

    @Override
    public DriverBean getByID(Integer id) {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        DriverBean driverBean = new DriverBean();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, NAME, SURNAME, GENDER, PHONE FROM AUTOBASE.DRIVERS " +
                    "WHERE ID = '" + id + "' ORDER BY ID");
            List<DriverBean> lines = parseTableLines(rs);
            if (lines.size() > 0) {
                driverBean.setId(lines.get(0).getId());
                driverBean.setName(lines.get(0).getName());
                driverBean.setSurname(lines.get(0).getSurname());
                driverBean.setGender(lines.get(0).getGender());
                driverBean.setPhone(lines.get(0).getPhone());
            }
            log.info("One record was selected in table [DRIVERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return driverBean;
    }

    @Override
    public void add(DriverBean driver) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.DRIVERS(NAME,SURNAME,GENDER,PHONE) VALUES(?,?,?,?)");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
            stmt.setString(3, driver.getGender());
            stmt.setInt(4, driver.getPhone());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more or less than 1 record: " + rows);
            }
            log.info("One record was inserted in table [DRIVERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(DriverBean driver) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.DRIVERS SET NAME=?, SURNAME=?, GENDER=?, PHONE=? WHERE ID=?");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
            stmt.setString(3, driver.getGender());
            stmt.setInt(4, driver.getPhone());
            stmt.setInt(5, driver.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more or less than 1 record: " + rows);
            }
            log.info("One record was updated in table [DRIVERS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(DriverBean driver) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM AUTOBASE.DRIVERS WHERE ID=?");
            stmt.setInt(1, driver.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more or less than 1 record: " + rows);
            }
            log.info("One record was deleted in table [DRIVERS]");
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
                driver.setName(rs.getString("NAME"));
                driver.setSurname(rs.getString("SURNAME"));
                driver.setGender(rs.getString("GENDER"));
                driver.setPhone(rs.getInt("PHONE"));
                result.add(driver);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
