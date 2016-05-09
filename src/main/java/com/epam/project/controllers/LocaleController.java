package com.epam.project.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LocaleController")
public class LocaleController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        if (lang != null) {
            log.info("Change locale to '" + lang + "' language");
            request.getSession(true).setAttribute("locale", lang);
            String url = (String) request.getSession().getAttribute("url");
            response.sendRedirect(url);
        } else {
            log.warn("Bad request for Locale Controller");
        }
    }

}