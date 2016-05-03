package com.epam.project.controllers;

import com.epam.project.beans.CarSetBean;
import com.epam.project.beans.ResultSetBean;
import com.epam.project.beans.RouteSetBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JournalController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResultSet rs;
        Statement stmt;
        ResultSet rs_2;
        Statement stmt_2;
        ResultSet rs_3;
        Statement stmt_3;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();

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
            //con.close();
            request.getRequestDispatcher("journal.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}