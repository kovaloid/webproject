package com.epam.project.controllers.journal.data_controls;

import com.epam.project.database.connection_pool.ConnectionPool;
import com.epam.project.service.DateMaker;
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
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/AddJournalController")
public class AddJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            String day_out = request.getParameter("day_out");
            String month_out = request.getParameter("month_out");
            String year_out = request.getParameter("year_out");
            String date_out = DateMaker.make(day_out, month_out, year_out);

            stmt = con.createStatement();
            int rows = stmt.executeUpdate("INSERT INTO KOVAL.JOURNAL(TIME_OUT,AUTO_ID,ROUTE_ID) VALUES('" + date_out + "','" + request.getParameter("num") + "','" + request.getParameter("route") + "')");

            log.info(rows + " row(s) was inserted");
            request.getRequestDispatcher("JournalController").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }
}