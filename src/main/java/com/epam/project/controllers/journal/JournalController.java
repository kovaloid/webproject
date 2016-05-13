package com.epam.project.controllers.journal;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.lines.JournalBean;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.*;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/JournalController")
public class JournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO<JournalBean> dao = new JournalDAO();
        TableBean journal = dao.getAll();
        request.getSession().setAttribute("journal_rs", journal);

        DAO<CarBean> dao2 = new CarsDAO();
        TableBean cars = dao2.getAll();
        request.getSession().setAttribute("cars_set", cars);

        DAO<RouteBean> dao3 = new RoutesDAO();
        TableBean routes = dao3.getAll();
        request.getSession().setAttribute("routes_set", routes);

        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/journal.jsp").forward(request, response);
    }
}