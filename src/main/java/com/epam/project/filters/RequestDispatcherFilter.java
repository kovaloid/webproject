package com.epam.project.filters;

import com.epam.project.service.constants.Commands;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


public class RequestDispatcherFilter implements Filter {

    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        String param = req.getParameter("command");
        if (param == null) {
            log.info("bad param");
        } else {
            if (param.equals(Commands.LOGIN) ||
                    param.equals(Commands.LOGOUT) ||
                    param.equals(Commands.SIGNUP) ||
                    param.equals(Commands.LOCALE) ||
                    param.equals(Commands.JOURNAL) ||
                    param.equals(Commands.DRIVERS) ||
                    param.equals(Commands.ROUTES) ||
                    param.equals(Commands.CARS)) {


                chain.doFilter(request, response);
            } else {
                log.info("bad param");
            }
        }

    }

    @Override
    public void destroy() {
    }
}