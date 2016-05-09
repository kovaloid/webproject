package com.epam.project.controllers.drivers.data_controls;

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

@WebServlet("/UpdateDriversController")
public class UpdateDriversController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            int rows = stmt.executeUpdate("UPDATE KOVAL.AUTO_PERSONNEL SET FIRST_NAME='" + request.getParameter("first_name") + "', LAST_NAME='" + request.getParameter("last_name") + "' WHERE ID='" + request.getParameter("id") + "'");

            log.info(rows + " row(s) was updated");
            request.getRequestDispatcher("DriversController").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }
}