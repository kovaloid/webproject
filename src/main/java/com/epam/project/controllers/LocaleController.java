package com.epam.project.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LocaleController")
public class LocaleController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        if (lang != null) {
            request.getSession(true).setAttribute("locale", lang);
            log.info("Change locale to '" + lang.toUpperCase() + "' language");

            Cookie localeCookie = new Cookie("locale", lang);
            localeCookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(localeCookie);

            String url = (String) request.getSession().getAttribute("url");
            response.sendRedirect(url);
        } else {
            log.warn("Bad request to Locale Controller");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}