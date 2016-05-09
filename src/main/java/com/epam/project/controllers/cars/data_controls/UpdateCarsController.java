package com.epam.project.controllers.cars.data_controls;

import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/UpdateCarsController")
public class UpdateCarsController extends HttpServlet {

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
            int rows = stmt.executeUpdate("UPDATE KOVAL.AUTO SET NUM='" + request.getParameter("num") + "', MARK='" + request.getParameter("mark") + "', COLOR='" + request.getParameter("color") + "', PERSONNEL_ID='" + request.getParameter("driver") + "' WHERE ID='" + request.getParameter("id") + "'");

            log.info(rows + " row(s) was updated");
            request.getRequestDispatcher("CarsController").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }
}