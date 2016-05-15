package com.epam.project.controllers.journal;

import com.epam.project.beans.Table;
import com.epam.project.database.dao.*;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import com.epam.project.util.MonthDefiner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/JournalController")
public class JournalController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO journalDAO = new JournalDAO();
        Table journalTable = journalDAO.getAll();
        request.getSession().setAttribute("journal_table", journalTable);

        ReadyDAO carsDAO = new CarsDAO();
        Table carsTable = carsDAO.getAllReady();
        request.getSession().setAttribute("cars_list", carsTable);

        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();
        request.getSession().setAttribute("routes_list", routesTable);

        List<String> monthsList = MonthDefiner.getMonthsList();
        request.getSession().setAttribute("months_list", monthsList);

        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/journal.jsp").forward(request, response);
    }
}