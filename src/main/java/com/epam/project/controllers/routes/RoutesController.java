package com.epam.project.controllers.routes;

import com.epam.project.beans.TableBean;
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

@WebServlet("/RoutesController")
public class RoutesController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DAO<RouteBean> dao = new RoutesDAO();

        TableBean routes = dao.getAll();
        request.getSession().setAttribute("routes_table", routes);
        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/routes.jsp").forward(request, response);
    }

}