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


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd;

        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("auth/success.jsp");
            UserBean user = new UserBean(username, password);
            request.setAttribute("user", user);


            session.setAttribute("login", username);
            session.setAttribute("password", password);
        } else {
            rd = request.getRequestDispatcher("auth/error.jsp");
        }
        rd.forward(request, response);
    }

}
