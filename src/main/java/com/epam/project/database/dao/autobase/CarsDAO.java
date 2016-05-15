package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.TableBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;
import com.epam.project.database.dao.ReadyDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarsDAO extends AbstractDAO<CarBean> implements ReadyDAO<CarBean> {

    @Override
    public Table<CarBean> getAll() {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<CarBean> carsTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT CARS.ID, CAR_NUMBER, COLOR, BRAND, NAME, SURNAME, READY FROM AUTOBASE.CARS " +
                    "JOIN AUTOBASE.DRIVERS ON DRIVER_ID = DRIVERS.ID ORDER BY CARS.ID");
            List<String> headers = parseTableHeaders(rs);
            List<CarBean> lines = parseTableLines(rs);
            carsTable.setHeaders(headers);
            carsTable.setLines(lines);
            carsTable.setCountColumns(headers.size());
            carsTable.setCountLines(lines.size());
            log.info("All records were selected in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return carsTable;
    }

    @Override
    public CarBean getByID(Integer id) {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        CarBean carBean = new CarBean();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT CARS.ID, CAR_NUMBER, COLOR, BRAND, NAME, SURNAME, READY FROM AUTOBASE.CARS " +
                    "JOIN AUTOBASE.DRIVERS ON DRIVER_ID = DRIVERS.ID WHERE CARS.ID = '" + id + "' ORDER BY CARS.ID");
            List<CarBean> lines = parseTableLines(rs);
            if (lines.size() > 0) {
                carBean.setId(lines.get(0).getId());
                carBean.setNumber(lines.get(0).getNumber());
                carBean.setColor(lines.get(0).getColor());
                carBean.setBrand(lines.get(0).getBrand());
                carBean.setDriverName(lines.get(0).getDriverName());
                carBean.setDriverSurname(lines.get(0).getDriverSurname());
                carBean.setReady(lines.get(0).getReady());
            }
            log.info("One record was selected in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return carBean;
    }

    @Override
    public Table<CarBean> getAllReady() {
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<CarBean> carsTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, CAR_NUMBER FROM AUTOBASE.CARS WHERE READY='да'");
            List<CarBean> lines = parseTableLinesReady(rs);
            carsTable.setLines(lines);
            carsTable.setCountLines(lines.size());
            log.info("Ready records were selected in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return carsTable;
    }

    @Override
    public void add(CarBean car) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.CARS(CAR_NUMBER,BRAND,COLOR,DRIVER_ID,READY) VALUES(?,?,?,?,?)");
            stmt.setString(1, car.getNumber());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getColor());
            stmt.setInt(4, car.getDriverId());
            stmt.setString(5, car.getReady());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more or less than 1 record: " + rows);
            }
            log.info("One record was inserted in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(CarBean car) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.CARS SET CAR_NUMBER=?, BRAND=?, COLOR=?, DRIVER_ID=?, READY=? WHERE ID=?");
            stmt.setString(1, car.getNumber());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getColor());
            stmt.setInt(4, car.getDriverId());
            stmt.setString(5, car.getReady());
            stmt.setInt(6, car.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more or less than 1 record: " + rows);
            }
            log.info("One record was updated in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(CarBean car) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM AUTOBASE.CARS WHERE ID=?");
            stmt.setInt(1, car.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more or less than 1 record: " + rows);
            }
            log.info("One record was deleted in table [CARS]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    protected List<CarBean> parseTableLines(ResultSet rs) {
        List<CarBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                CarBean car = new CarBean();
                car.setId(rs.getInt("ID"));
                car.setNumber(rs.getString("CAR_NUMBER"));
                car.setColor(rs.getString("COLOR"));
                car.setBrand(rs.getString("BRAND"));
                car.setDriverName(rs.getString("NAME"));
                car.setDriverSurname(rs.getString("SURNAME"));
                car.setReady(rs.getString("READY"));
                result.add(car);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }

    private List<CarBean> parseTableLinesReady(ResultSet rs) {
        List<CarBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                CarBean car = new CarBean();
                car.setId(rs.getInt("ID"));
                car.setNumber(rs.getString("CAR_NUMBER"));
                result.add(car);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
