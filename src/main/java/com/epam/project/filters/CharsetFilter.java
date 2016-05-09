package com.epam.project.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;

    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("charEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        log.info("Charset '" + encoding + "' was set");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
