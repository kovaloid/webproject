package com.epam.project.controllers.routes;

import com.epam.project.beans.Table;
import com.epam.project.consts.Account;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();
        request.getSession().setAttribute("routes_table", routesTable);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (!status.equals(Account.Status.IN)) response.sendError(401);
        else request.getRequestDispatcher("/WEB-INF/jsp/data_tables/cars.jsp").forward(request, response);
    }
}