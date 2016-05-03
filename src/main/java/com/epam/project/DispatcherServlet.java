package com.epam.project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String command = request.getParameter("command");
        switch (command) {
            case "locale":
                request.getRequestDispatcher("LocaleController").forward(request, response);
                break;
            case "login":
                request.getRequestDispatcher("LoginController").forward(request, response);
                break;
            case "logout":
                request.getRequestDispatcher("LogoutController").forward(request, response);
                break;
            case "reg":
                request.getRequestDispatcher("RegController").forward(request, response);
                break;
            case "cars":
                request.getRequestDispatcher("CarsController").forward(request, response);
                break;
            case "drivers":
                request.getRequestDispatcher("DriversController").forward(request, response);
                break;
            case "routes":
                request.getRequestDispatcher("RoutesController").forward(request, response);
                break;
            case "journal":
                request.getRequestDispatcher("JournalController").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("ErrorController").forward(request, response);
        }

    }

}
