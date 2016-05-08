package com.epam.project.controllers.account;

import com.epam.project.service.AccountManager;
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

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {

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
            AccountManager m = new AccountManager();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String repeat = request.getParameter("repeat");
            String result = m.signup(username, password, repeat);
            if (result.equals("success")) {
                int countRows = stmt.executeUpdate("INSERT INTO KOVAL.USERS(LOGIN,PASSWORD) VALUES('" + request.getParameter("username") + "','" + request.getParameter("password") + "')");
            } else
                log.info("sign up error");

            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("ErrorController").forward(request, response);
        }
    }
}