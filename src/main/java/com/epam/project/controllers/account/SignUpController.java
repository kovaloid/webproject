package com.epam.project.controllers.account;

import com.epam.project.database.pool.ConnectionPool;
import com.epam.project.service.AccountManager;
import com.epam.project.service.constants.Account;
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

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            String username = request.getParameter(Account.USERNAME);
            String password = request.getParameter(Account.PASSWORD);
            String repeat = request.getParameter(Account.REPEAT);

            AccountManager account = new AccountManager();
            String result = account.signup(username, password, repeat);

            if (result.equals(Account.Result.SUCCESS)) {
                stmt = con.createStatement();
                int rows = stmt.executeUpdate("INSERT INTO KOVAL.USERS(LOGIN,PASSWORD) VALUES('" + username + "','" + password + "')");

                log.info(rows + " user(s) was inserted");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                log.warn("User has problems with sign up");
                request.setAttribute("result_signup", result);
                request.getRequestDispatcher("/WEB-INF/jsp/account/fail_signup.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeConnection(con, stmt);
        }
    }
}