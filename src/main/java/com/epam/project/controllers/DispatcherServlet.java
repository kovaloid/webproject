package com.epam.project.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class DispatcherServlet extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String doParam;
        String command = request.getParameter("command");
        switch (command) {
            case "locale":
                request.getRequestDispatcher("LocaleController").forward(request, response);
                break;
            case "login":
                request.getRequestDispatcher("LogInController").forward(request, response);
                break;
            case "logout":
                request.getRequestDispatcher("LogOutController").forward(request, response);
                break;
            case "signup":
                request.getRequestDispatcher("SignUpController").forward(request, response);
                break;
            case "cars":
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("CarsController").forward(request, response);
                else
                    switch (doParam) {
                        case "add":
                            request.getRequestDispatcher("AddCarsController").forward(request, response);
                            break;
                        case "update":
                            request.getRequestDispatcher("UpdateCarsController").forward(request, response);
                            break;
                        case "remove":
                            request.getRequestDispatcher("RemoveCarsController").forward(request, response);
                            break;
                    }
                break;
            case "drivers":
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("DriversController").forward(request, response);
                else
                    switch (doParam) {
                        case "add":
                            request.getRequestDispatcher("AddDriversController").forward(request, response);
                            break;
                        case "update":
                            request.getRequestDispatcher("UpdateDriversController").forward(request, response);
                            break;
                        case "remove":
                            request.getRequestDispatcher("RemoveDriversController").forward(request, response);
                            break;
                    }
                break;
            case "routes":
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("RoutesController").forward(request, response);
                else
                    switch (doParam) {
                        case "add":
                            request.getRequestDispatcher("AddRoutesController").forward(request, response);
                            break;
                        case "update":
                            request.getRequestDispatcher("UpdateRoutesController").forward(request, response);
                            break;
                        case "remove":
                            request.getRequestDispatcher("RemoveRoutesController").forward(request, response);
                            break;
                    }
                break;
            case "journal":
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("JournalController").forward(request, response);
                else
                    switch (doParam) {
                        case "add":
                            request.getRequestDispatcher("AddJournalController").forward(request, response);
                            break;
                        case "update":
                            request.getRequestDispatcher("UpdateJournalController").forward(request, response);
                            break;
                        case "remove":
                            request.getRequestDispatcher("RemoveJournalController").forward(request, response);
                            break;
                    }
                break;
            default:
                log.warn("Bad request for Dispatcher Servlet");
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
