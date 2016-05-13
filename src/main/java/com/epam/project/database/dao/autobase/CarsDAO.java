package com.epam.project.database.dao.autobase;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.TableBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarsDAO extends AbstractDAO<CarBean> {

    @Override
    public TableBean getAll() {
        Connection con = pool.takeConnection();
        TableBean<CarBean> carsTable = new TableBean<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT AUTO.ID, NUM, COLOR, MARK, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO "
                    + "JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID ORDER BY AUTO.ID");
            List<String> headers = parseTableHeaders(rs);
            List<CarBean> lines = parseTableLines(rs);
            carsTable.setHeaders(headers);
            carsTable.setLines(lines);
            carsTable.setCountColumns(headers.size());
            carsTable.setCountLines(lines.size());
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
            stmt = con.prepareStatement("INSERT INTO KOVAL.AUTO(NUM,MARK,COLOR,PERSONNEL_ID) VALUES(?,?,?,?)");
            stmt.setString(1, car.getNumber());
            stmt.setString(2, car.getMark());
            stmt.setString(3, car.getColor());
            stmt.setString(4, car.getDriverId());
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
    public void update(CarBean car) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE KOVAL.AUTO SET NUM=?, MARK=?, COLOR=?, PERSONNEL_ID=? WHERE ID=?");
            stmt.setString(1, car.getNumber());
            stmt.setString(2, car.getMark());
            stmt.setString(3, car.getColor());
            stmt.setString(4, car.getDriverId());
            stmt.setInt(5, car.getId());
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
    public void remove(CarBean car) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM KOVAL.AUTO WHERE ID=?");
            stmt.setInt(1, car.getId());
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
    protected List<CarBean> parseTableLines(ResultSet rs) {
        List<CarBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                CarBean car = new CarBean();
                car.setId(rs.getInt("ID"));
                car.setNumber(rs.getString("NUM"));
                car.setColor(rs.getString("COLOR"));
                car.setMark(rs.getString("MARK"));
                car.setDriverName(rs.getString("FIRST_NAME"));
                car.setDriverSurname(rs.getString("LAST_NAME"));
                result.add(car);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
