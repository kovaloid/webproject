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

@WebServlet("/AddJournalController")
public class AddJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Statement stmt;
        Statement stmt_2;
        Statement stmt_3;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();

            stmt = con.createStatement();


            //stmt_2 = con.createStatement();
            //stmt_3 = con.createStatement();

            //ResultSet rs_2 = stmt.executeQuery("SELECT ID FROM KOVAL.AUTO WHERE NUM = '" + request.getParameter("num") + "'");
            //ResultSet rs_3 = stmt.executeQuery("");


            String day_out = request.getParameter("day_out");
            String month_out = request.getParameter("month_out");
            String year_out = request.getParameter("year_out");


            String date_out = DateMaker.make(day_out, month_out, year_out);


            int countRows = stmt.executeUpdate("INSERT INTO KOVAL.JOURNAL(TIME_OUT,AUTO_ID,ROUTE_ID) VALUES('" + date_out + "','" + request.getParameter("num") + "','" + request.getParameter("route") + "')");


            request.getRequestDispatcher("JournalController").forward(request, response);

        } catch (SQLException | NamingException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}