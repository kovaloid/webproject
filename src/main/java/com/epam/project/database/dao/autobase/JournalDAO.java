package com.epam.project.database.dao.autobase;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JournalDAO extends AbstractDAO<JournalBean> {

    @Override
    public Table<JournalBean> getAll() {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Table<JournalBean> journalTable = new TableBean<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT JOURNAL.ID, CAR_NUMBER, DATE_OUT, DATE_IN, ROUTE_NAME, SURNAME FROM AUTOBASE.JOURNAL "
                    + "JOIN AUTOBASE.CARS ON CAR_ID = CARS.ID "
                    + "JOIN AUTOBASE.ROUTES ON ROUTE_ID = ROUTES.ID "
                    + "JOIN AUTOBASE.DRIVERS ON DRIVER_ID = DRIVERS.ID "
                    + "ORDER BY JOURNAL.ID");
            List<String> headers = parseTableHeaders(rs);
            List<JournalBean> lines = parseTableLines(rs);
            journalTable.setHeaders(headers);
            journalTable.setLines(lines);
            journalTable.setCountColumns(headers.size());
            journalTable.setCountLines(lines.size());
            log.info("All records were selected in table [JOURNAL]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return journalTable;
    }

    @Override
    public JournalBean getByID(Integer id) {
        assert pool != null;
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        JournalBean journalBean = new JournalBean();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT JOURNAL.ID, CAR_NUMBER, DATE_OUT, DATE_IN, ROUTE_NAME, SURNAME FROM AUTOBASE.JOURNAL "
                    + "JOIN AUTOBASE.CARS ON CAR_ID = CARS.ID "
                    + "JOIN AUTOBASE.ROUTES ON ROUTE_ID = ROUTES.ID "
                    + "JOIN AUTOBASE.DRIVERS ON DRIVER_ID = DRIVERS.ID "
                    + "WHERE JOURNAL.ID = '" + id + "' "
                    + "ORDER BY JOURNAL.ID");
            List<JournalBean> lines = parseTableLines(rs);
            if (lines.size() > 0) {
                journalBean.setId(lines.get(0).getId());
                journalBean.setNumber(lines.get(0).getNumber());
                journalBean.setDateOut(lines.get(0).getDateOut());
                journalBean.setDateIn(lines.get(0).getDateIn());
                journalBean.setRouteName(lines.get(0).getRouteName());
                journalBean.setDriverSurname(lines.get(0).getDriverSurname());
            }
            log.info("One record was selected in table [JOURNAL]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }
        return journalBean;
    }

    @Override
    public void add(JournalBean journal) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO AUTOBASE.JOURNAL(DATE_OUT,CAR_ID,ROUTE_ID) VALUES(?,?,?)");
            stmt.setDate(1, journal.getDateOut());
            stmt.setInt(2, journal.getCarId());
            stmt.setInt(3, journal.getRouteId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On insert modify more or less than 1 record: " + rows);
            }
            log.info("One record was inserted in table [JOURNAL]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void update(JournalBean journal) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE AUTOBASE.JOURNAL SET DATE_IN=? WHERE ID=?");
            stmt.setDate(1, journal.getDateIn());
            stmt.setInt(2, journal.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On update modify more or less than 1 record: " + rows);
            }
            log.info("One record was updated in table [JOURNAL]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    public void remove(JournalBean journal) {
        assert pool != null;
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM AUTOBASE.JOURNAL WHERE ID=?");
            stmt.setInt(1, journal.getId());
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DAOException("On delete modify more or less than 1 record: " + rows);
            }
            log.info("One record was deleted in table [JOURNAL]");
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }

    @Override
    protected List<JournalBean> parseTableLines(ResultSet rs) {
        List<JournalBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                JournalBean journal = new JournalBean();
                journal.setId(rs.getInt("ID"));
                journal.setNumber(rs.getString("CAR_NUMBER"));
                journal.setDateOut(rs.getDate("DATE_OUT"));
                journal.setDateIn(rs.getDate("DATE_IN"));
                journal.setRouteName(rs.getString("ROUTE_NAME"));
                journal.setDriverSurname(rs.getString("SURNAME"));
                result.add(journal);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }
}
