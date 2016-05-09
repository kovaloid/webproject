package com.epam.project.controllers.journal;

import com.epam.project.beans.select_box.CarSetBean;
import com.epam.project.beans.ResultSetBean;
import com.epam.project.beans.select_box.RouteSetBean;
import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/JournalController")
public class JournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt_2 = null;
        ResultSet rs_2 = null;
        Statement stmt_3 = null;
        ResultSet rs_3 = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT JOURNAL.ID, NUM, TIME_IN, TIME_OUT, NAME, LAST_NAME FROM KOVAL.JOURNAL "
                    + "JOIN KOVAL.AUTO ON AUTO_ID = AUTO.ID "
                    + "JOIN KOVAL.ROUTES ON ROUTE_ID = ROUTES.ID "
                    + "JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID "
                    + "ORDER BY JOURNAL.ID");

            stmt_2 = con.createStatement();
            rs_2 = stmt_2.executeQuery("SELECT ID, NUM, MARK FROM KOVAL.AUTO");

            stmt_3 = con.createStatement();
            rs_3 = stmt_3.executeQuery("SELECT ID, NAME FROM KOVAL.ROUTES");

            request.getSession().setAttribute("journal_rs", new ResultSetBean(rs));
            request.getSession().setAttribute("cars_set", new CarSetBean(rs_2));
            request.getSession().setAttribute("routes_set", new RouteSetBean(rs_3));
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/journal.jsp").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeStatement(stmt_2, rs_2);
            pool.closeStatement(stmt_3, rs_3);
            pool.closeConnection(con, stmt, rs);
        }
    }
}