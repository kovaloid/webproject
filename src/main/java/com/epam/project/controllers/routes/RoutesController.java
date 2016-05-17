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

/**
 * Routes Controller.
 * <p>This controller gets data table from Routes DAO, separate it into
 * small tables and transfers to JSP, where this tables are built.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/RoutesController")
public class RoutesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();

        Table[] routesTablesArray = TableSeparator.separate(routesTable);
        int routesTablesAmount = routesTablesArray.length;
        int routesTableNumber = TableSeparator.getTableNumber(request.getParameter("number"), routesTablesAmount);

        request.getSession().setAttribute("routes_table_number", routesTableNumber);
        request.getSession().setAttribute("routes_tables_amount", routesTablesAmount);
        if (routesTablesAmount != 0)
            request.getSession().setAttribute("routes_table", routesTablesArray[routesTableNumber - 1]);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/routes.jsp").forward(request, response);
    }
}