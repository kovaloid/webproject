package com.epam.project.database.dao;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.database.beans.Driver;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriversDAO implements GenericDAO<Driver> {

    private ConnectionPool pool;
    private final static Logger log = Logger.getRootLogger();

    public DriversDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public List<Driver> getAll() {
        Connection con = pool.takeConnection();
        List<Driver> list = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY ID");
            list = parseResultSet(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return list;
    }

    @Override
    public void add(Driver driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO KOVAL.AUTO_PERSONNEL(FIRST_NAME,LAST_NAME) VALUES(?,?)");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
            int rows = stmt.executeUpdate();
            //if (count != 1) {
            //    throw new PersistException("On update modify more then 1 record: " + count);
            //}
            log.info(rows + " row(s) was inserted");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(Driver driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE KOVAL.AUTO_PERSONNEL SET FIRST_NAME=?, LAST_NAME=? WHERE ID=?");
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getSurname());
            stmt.setInt(3, driver.getId());
            int rows = stmt.executeUpdate();
            //if (count != 1) {
            //    throw new PersistException("On update modify more then 1 record: " + count);
            //}
            log.info(rows + " row(s) was updated");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(Driver driver) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID=?");
            stmt.setInt(1, driver.getId());
            int rows = stmt.executeUpdate();
            //if (count != 1) {
            //    throw new PersistException("On update modify more then 1 record: " + count);
            //}
            log.info(rows + " row(s) was deleted");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    private List<Driver> parseResultSet(ResultSet rs) {
        LinkedList<Driver> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Driver driver = new Driver();
                //driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("first_name"));
                driver.setSurname(rs.getString("second_name"));
                result.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
