package com.epam.project.controllers.account;

import com.epam.project.service.AccountManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogInController")
public class LogInController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    public LogInController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String successUrl = (String) session.getAttribute("successUrl");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountManager authenticator = new AccountManager();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {

            //UserBean user = new UserBean(username, password);
            //request.setAttribute("user", user);

            session.setAttribute("status", "in");
            session.setAttribute("login", username);
            session.setAttribute("password", password);
            if(username.equals("admin"))
                session.setAttribute("role", "admin");
            else
                session.setAttribute("role", "client");



            //request.login(username, password);
            //request.authenticate(response);


            response.sendRedirect(successUrl);
        } else {
            request.getRequestDispatcher("account/fail_auth.jsp").forward(request, response);
        }
    }

}
