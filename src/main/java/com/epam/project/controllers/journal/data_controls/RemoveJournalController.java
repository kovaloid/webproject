package com.epam.project.controllers.journal.data_controls;

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
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/RemoveJournalController")
public class RemoveJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            int rows = stmt.executeUpdate("DELETE FROM KOVAL.JOURNAL WHERE ID='" + request.getParameter("id") + "'");

            log.info(rows + " row(s) was deleted");
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