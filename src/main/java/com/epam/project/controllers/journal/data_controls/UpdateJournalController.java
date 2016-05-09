package com.epam.project.controllers.journal.data_controls;

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
        Statement stmt;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();

            stmt = con.createStatement();


            String day_in = request.getParameter("day_in");
            String month_in = request.getParameter("month_in");
            String year_in = request.getParameter("year_in");


            String date_in = DateMaker.make(day_in, month_in, year_in);

            //String date_in = request.getParameter("day_in")+"-"+request.getParameter("month_in")+"-"+request.getParameter("year_in");



            int countRows = stmt.executeUpdate("UPDATE KOVAL.JOURNAL SET TIME_IN='" + date_in + "' WHERE ID='" + request.getParameter("id") + "'");




            request.getRequestDispatcher("JournalController").forward(request, response);

        } catch (SQLException | NamingException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}