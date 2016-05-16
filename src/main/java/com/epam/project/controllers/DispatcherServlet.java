package com.epam.project.controllers;

import com.epam.project.consts.Commands;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Dispatcher Servlet Controller.
 * <p>It's main controller of application. All requests go through it.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
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
            case Commands.LOCALE:
                request.getRequestDispatcher("LocaleController").forward(request, response);
                break;
            case Commands.LOGIN:
                request.getRequestDispatcher("LogInController").forward(request, response);
                break;
            case Commands.LOGOUT:
                request.getRequestDispatcher("LogOutController").forward(request, response);
                break;
            case Commands.SIGNUP:
                request.getRequestDispatcher("SignUpController").forward(request, response);
                break;
            case Commands.CARS:
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("CarsController").forward(request, response);
                else
                    switch (doParam) {
                        case Commands.ADD:
                            request.getRequestDispatcher("AddCarsController").forward(request, response);
                            break;
                        case Commands.UPDATE:
                            request.getRequestDispatcher("UpdateCarsController").forward(request, response);
                            break;
                        case Commands.REMOVE:
                            request.getRequestDispatcher("RemoveCarsController").forward(request, response);
                            break;
                    }
                break;
            case Commands.DRIVERS:
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("DriversController").forward(request, response);
                else
                    switch (doParam) {
                        case Commands.ADD:
                            request.getRequestDispatcher("AddDriversController").forward(request, response);
                            break;
                        case Commands.UPDATE:
                            request.getRequestDispatcher("UpdateDriversController").forward(request, response);
                            break;
                        case Commands.REMOVE:
                            request.getRequestDispatcher("RemoveDriversController").forward(request, response);
                            break;
                    }
                break;
            case Commands.ROUTES:
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("RoutesController").forward(request, response);
                else
                    switch (doParam) {
                        case Commands.ADD:
                            request.getRequestDispatcher("AddRoutesController").forward(request, response);
                            break;
                        case Commands.UPDATE:
                            request.getRequestDispatcher("UpdateRoutesController").forward(request, response);
                            break;
                        case Commands.REMOVE:
                            request.getRequestDispatcher("RemoveRoutesController").forward(request, response);
                            break;
                    }
                break;
            case Commands.JOURNAL:
                doParam = request.getParameter("do");
                if (doParam == null)
                    request.getRequestDispatcher("JournalController").forward(request, response);
                else
                    switch (doParam) {
                        case Commands.ADD:
                            request.getRequestDispatcher("AddJournalController").forward(request, response);
                            break;
                        case Commands.UPDATE:
                            request.getRequestDispatcher("UpdateJournalController").forward(request, response);
                            break;
                        case Commands.REMOVE:
                            request.getRequestDispatcher("RemoveJournalController").forward(request, response);
                            break;
                    }
                break;
            default:
                log.warn("Bad request to Dispatcher Servlet");
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
