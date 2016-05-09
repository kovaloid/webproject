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

@WebServlet("/UpdateJournalController")
public class UpdateJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            String day_in = request.getParameter("day_in");
            String month_in = request.getParameter("month_in");
            String year_in = request.getParameter("year_in");
            String date_in = DateMaker.make(day_in, month_in, year_in);

            stmt = con.createStatement();
            int rows = stmt.executeUpdate("UPDATE KOVAL.JOURNAL SET TIME_IN='" + date_in + "' WHERE ID='" + request.getParameter("id") + "'");

            log.info(rows + " row(s) was updated");
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