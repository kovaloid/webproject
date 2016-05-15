package com.epam.project.controllers.routes;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.Line;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import com.epam.project.utils.TableSeparator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RoutesController")
public class RoutesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();

        Table[] tables = TableSeparator.separate(2, routesTable);



        request.getSession().setAttribute("routes_table", tables[0]);
        request.getSession().setAttribute("routes_table2", tables[1]);
        //request.getSession().setAttribute("routes_table", routesTable);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/routes.jsp").forward(request, response);
    }
}