package com.epam.project.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LocaleController")
public class LocaleController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        if (lang != null) {
            session.setAttribute("locale", lang);
            log.info("Change locale to '" + lang.toUpperCase() + "' language");

            String redirectURL = (String) session.getAttribute("redirectURL");
            response.sendRedirect(redirectURL);
        } else {
            log.warn("Bad request to Locale Controller");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}