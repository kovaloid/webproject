package com.epam.project.controllers.drivers.data_controls;

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

@WebServlet("/RemoveDriversController")
public class RemoveDriversController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Statement stmt;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();

            stmt = con.createStatement();
            int countRows = stmt.executeUpdate("DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID='" + request.getParameter("id") + "'");

            request.getRequestDispatcher("DriversController").forward(request, response);

        } catch (SQLException | NamingException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}