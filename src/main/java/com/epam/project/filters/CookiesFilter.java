package com.epam.project.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CookiesFilter implements Filter {
    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.isRequestedSessionIdFromCookie()) {
            log.info("[FILTER] Cookies enabled");
        } else {
            log.warn("[FILTER] Cookies disabled");
            request.setAttribute("exception", "Cookies disabled, please enable it");
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }

        Cookie[] cookies = req.getCookies();
        StringBuffer cookiesLog = new StringBuffer();
        cookiesLog.append("[FILTER] Cookies: { ");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookiesLog.append("[").append(cookie.getName()).append(" = ").append(cookie.getValue()).append("] ");
            }
        }
        cookiesLog.append("}");
        log.info(cookiesLog);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}