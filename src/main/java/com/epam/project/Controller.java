package com.epam.project;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("first tomcat log message");

        /*HttpSession session = request.getSession(true);


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Title</title></head>");
        out.println("<body>brilliant");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        out.println(login + "<br>");
        out.println(password + "<br>");
        out.println("</body></html>");*/




        response.setContentType("text/html; charset=utf-8");
        //response.setHeader();

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Title</title>" +
                "<link rel=\"stylesheet\" href=\"/bootstrap/css/bootstrap.css\" type=\"text/css\" />" +
                "</head><body>");

        //Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            ////con = DriverManager.getConnection("jdbc:oracle:thin:system/koval@localhost");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "system", "koval");

            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
            Connection con = ds.getConnection();


            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY ID");
            out.println("<table class=\"table table-striped\" border=1>");
            out.println("<thead> <tr> <th>#</th><th>first name</th><th>last name</th></tr></thead><tbody>");
            //out.println("<th><td>1</td><td>1</td><td>1</td></th>");
            while (rs.next()) {
                out.println("<tr><td>"+rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>");
            }
            out.println("</tbody></table>");
        } catch (SQLException | NamingException e) {
            //} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        out.println("</body></html>");

    }

}
