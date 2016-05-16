package com.epam.project.controllers.journal.data_controls;

import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.utils.DateHelper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Add Journal Controller.
 * <p>This controller adds new record in Journal table.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/AddJournalController")
public class AddJournalController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String dayOut = request.getParameter("day_out");
            String monthOut = request.getParameter("month_out");
            String yearOut = request.getParameter("year_out");
            Date dateOut = DateHelper.convert(dayOut, monthOut, yearOut);

            Integer carId = Integer.valueOf(request.getParameter("car_id"));
            Integer routeId = Integer.valueOf(request.getParameter("route_id"));

            DAO<JournalBean> journalDAO = new JournalDAO();
            journalDAO.add(new JournalBean(dateOut, carId, routeId));

            request.getRequestDispatcher("JournalController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}