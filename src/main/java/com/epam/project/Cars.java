package com.epam.project;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class Cars extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        /*response.setContentType("text/html; charset=utf-8");


        Statement stmt;
        ResultSet rs;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();


            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY ID");


            request.setAttribute("rs", rs);*/

            //request.getRequestDispatcher("cars.jsp").forward(request, response);


            //while (rs.next()) {
            //    out.println("<tr><td>"+rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>");
            //}

        /*} catch (SQLException | NamingException e) {
            e.printStackTrace();
        }*/

        String locale = "ru";
        request.getSession().setAttribute("local", locale);






        Statement stmt;
        ResultSet rs;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();


            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY ID");

            rs.next();
            //Object o = rs;

            request.setAttribute("rs", rs);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }




        try {
            request.getRequestDispatcher("cars.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
