package com.epam.project.database.dao.autobase;

import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.AbstractDAO;
import com.epam.project.database.dao.DAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JournalDAO extends AbstractDAO<JournalBean> {

    public TableBean getAll() {
        Connection con = pool.takeConnection();
        TableBean<JournalBean> journalTable = new TableBean<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT JOURNAL.ID, NUM, TIME_OUT, TIME_IN, NAME, LAST_NAME FROM KOVAL.JOURNAL "
                    + "JOIN KOVAL.AUTO ON AUTO_ID = AUTO.ID "
                    + "JOIN KOVAL.ROUTES ON ROUTE_ID = ROUTES.ID "
                    + "JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID "
                    + "ORDER BY JOURNAL.ID");
            List<String> headers = parseTableHeaders(rs);
            List<JournalBean> lines = parseTableLines(rs);
            journalTable.setHeaders(headers);
            journalTable.setLines(lines);
            journalTable.setCountColumns(headers.size());
            journalTable.setCountLines(lines.size());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, stmt, rs);
        }

        //log.info(journalTable.getLines().get(1).getTimeIn().toString());
        //log.info(journalTable.getLines().get(2).getTimeIn().toString());
        //if(journalTable.getLines().get(3).getTimeIn().toString() == null) {
        //    log.info("HOLY SHIT!");
        //}
        //log.info(journalTable.getLines().get(3).getTimeIn().toString());
        return journalTable;
    }

    public void add(JournalBean journal) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO KOVAL.JOURNAL(TIME_OUT,AUTO_ID,ROUTE_ID) VALUES(?,?,?)");

            stmt.setDate(1, journal.getTimeOut());
            stmt.setInt(2, journal.getCarId());
            stmt.setInt(3, journal.getRouteId());

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

    public void update(JournalBean journal) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE KOVAL.JOURNAL SET TIME_IN=? WHERE ID=?");

            stmt.setDate(1, journal.getTimeIn());
            stmt.setInt(2, journal.getId());
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

    public void remove(JournalBean journal) {
        Connection con = pool.takeConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM KOVAL.JOURNAL WHERE ID=?");

            stmt.setInt(1, journal.getId());
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

    protected List<JournalBean> parseTableLines(ResultSet rs) {
        List<JournalBean> result = new LinkedList<>();
        try {
            while (rs.next()) {
                JournalBean journal = new JournalBean();
                journal.setId(rs.getInt("ID"));
                journal.setNumber(rs.getString("NUM"));
                journal.setTimeOut(rs.getDate("TIME_OUT"));
                journal.setTimeIn(rs.getDate("TIME_IN"));
                journal.setRouteName(rs.getString("NAME"));
                journal.setDriverSurname(rs.getString("LAST_NAME"));
                result.add(journal);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
        return result;
    }

}
