package com.epam.project.controllers;

import com.epam.project.Authenticator;
import com.epam.project.beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String successUrl = (String) session.getAttribute("successUrl");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Authenticator authenticator = new Authenticator();
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

            response.sendRedirect(successUrl);
        } else {
            request.getRequestDispatcher("account/auth_error.jsp").forward(request, response);
        }
    }

}
