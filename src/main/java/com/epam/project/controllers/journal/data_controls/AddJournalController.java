package com.epam.project.controllers.journal.data_controls;

import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.service.DateMaker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/AddJournalController")
public class AddJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DAO<JournalBean> dao = new JournalDAO();

            String day_out = request.getParameter("day_out");
            String month_out = request.getParameter("month_out");
            String year_out = request.getParameter("year_out");

            Date date_out = DateMaker.make(day_out, month_out, year_out);

            Integer carId = Integer.valueOf(request.getParameter("car_id"));
            Integer routeId = Integer.valueOf(request.getParameter("route_id"));

            dao.add(new JournalBean(date_out, carId, routeId));
            request.getRequestDispatcher("JournalController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}