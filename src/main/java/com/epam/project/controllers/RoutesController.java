package com.epam.project.controllers;

import com.epam.project.beans.ResultSetBean;

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

public class RoutesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Statement stmt;
        ResultSet rs;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, NAME FROM KOVAL.ROUTES ORDER BY ID");

            request.getSession().setAttribute("routes_rs", new ResultSetBean(rs));
            //con.close();
            request.getRequestDispatcher("routes.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}