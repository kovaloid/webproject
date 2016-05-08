package com.epam.project.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);

            log.info(req.getRemoteAddr() + "::RequestParams::{" + name + " = " + value + "}");

        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + " = " + cookie.getValue() + "}");

            }
        }



        if(req.isRequestedSessionIdFromCookie()) {
            log.info("cookie on");
        } else {
            log.info("cookie off");
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
