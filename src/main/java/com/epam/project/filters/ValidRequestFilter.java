package com.epam.project.filters;

import com.epam.project.consts.Commands;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class ValidRequestFilter implements Filter {
    private final static Logger log = Logger.getRootLogger();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        Enumeration<String> params = req.getParameterNames();
        StringBuffer requestLog = new StringBuffer();
        requestLog.append("[FILTER] Request Parameters: { ");
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            requestLog.append("[").append(name).append(" = ").append(value).append("] ");
        }
        requestLog.append("}");
        log.info(requestLog);

        String commandParam = req.getParameter("command");
        if (commandParam == null) {
            log.warn("[FILTER] Bad request to Dispatcher Servlet");
            req.getRequestDispatcher("index.jsp");
        } else {
            boolean validRequest = commandParam.equals(Commands.LOGIN) ||
                    commandParam.equals(Commands.LOGOUT) ||
                    commandParam.equals(Commands.SIGNUP) ||
                    commandParam.equals(Commands.LOCALE) ||
                    commandParam.equals(Commands.JOURNAL) ||
                    commandParam.equals(Commands.DRIVERS) ||
                    commandParam.equals(Commands.ROUTES) ||
                    commandParam.equals(Commands.CARS);
            if (validRequest)
                chain.doFilter(request, response);
            else {
                log.warn("[FILTER] Bad parameter in request to Dispatcher Servlet");
                req.getRequestDispatcher("index.jsp");
            }
        }
    }

    @Override
    public void destroy() {
    }
}