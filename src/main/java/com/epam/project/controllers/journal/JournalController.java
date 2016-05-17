package com.epam.project.controllers.journal;

import com.epam.project.beans.Table;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.*;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import com.epam.project.utils.MonthDefiner;
import com.epam.project.utils.TableSeparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Journal Controller.
 * <p>This controller gets data table from Journal DAO, separate it into small tables
 * and transfers to JSP, where this tables are built. Also controller gets Routes and
 * Ready Cars DAOs for build 'select box' lists in data control area on JSP</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/JournalController")
public class JournalController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO journalDAO = new JournalDAO();
        Table journalTable = journalDAO.getAll();

        Table[] journalTablesArray = TableSeparator.separate(journalTable);
        int journalTablesAmount = journalTablesArray.length;
        int journalTableNumber = TableSeparator.getTableNumber(request.getParameter("number"), journalTablesAmount);

        request.getSession().setAttribute("journal_table_number", journalTableNumber);
        request.getSession().setAttribute("journal_tables_amount", journalTablesAmount);
        if (journalTablesAmount != 0)
            request.getSession().setAttribute("journal_table", journalTablesArray[journalTableNumber - 1]);

        ReadyDAO carsDAO = new CarsDAO();
        Table carsTable = carsDAO.getAllReady();
        request.getSession().setAttribute("cars_list", carsTable);

        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();
        request.getSession().setAttribute("routes_list", routesTable);

        List<String> monthsList = MonthDefiner.getMonthsList();
        request.getSession().setAttribute("months_list", monthsList);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/journal.jsp").forward(request, response);
    }
}