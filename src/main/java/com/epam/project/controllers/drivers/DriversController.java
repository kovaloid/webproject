package com.epam.project.controllers.drivers;

import com.epam.project.beans.Table;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import com.epam.project.utils.TableSeparator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Drivers Controller.
 * <p>This controller gets data table from Drivers DAO, separate it into
 * small tables and transfers to JSP, where this tables are built.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/DriversController")
public class DriversController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO driversDAO = new DriversDAO();
        Table driversTable = driversDAO.getAll();

        Table[] driversTablesArray = TableSeparator.separate(driversTable);
        int driversTablesAmount = driversTablesArray.length;
        int driversTableNumber = TableSeparator.getTableNumber(request.getParameter("number"), driversTablesAmount);

        request.getSession().setAttribute("drivers_table_number", driversTableNumber);
        request.getSession().setAttribute("drivers_tables_amount", driversTablesAmount);
        request.getSession().setAttribute("drivers_table", driversTablesArray[driversTableNumber-1]);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/drivers.jsp").forward(request, response);
    }
}