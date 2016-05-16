package com.epam.project.filters;

import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class DBConnectionFilter implements Filter {
    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool == null) {
            log.info("[FILTER] Fail connection to database");
            request.getRequestDispatcher("/WEB-INF/jsp/errors/database.jsp").forward(request, response);
        } else {
            log.info("[FILTER] Success connection to database");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}