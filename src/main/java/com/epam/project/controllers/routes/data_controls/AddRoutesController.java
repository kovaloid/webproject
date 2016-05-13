package com.epam.project.controllers.routes.data_controls;

import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddRoutesController")
public class AddRoutesController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DAO<RouteBean> dao = new RoutesDAO();

            String name = request.getParameter("route_name");
            Integer length = Integer.valueOf(request.getParameter("route_length"));
            Integer price = Integer.valueOf(request.getParameter("route_name"));
            dao.add(new RouteBean(name, length, price));
            request.getRequestDispatcher("RoutesController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }

}