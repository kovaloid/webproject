package com.epam.project.controllers.drivers.data_controls;

import com.epam.project.beans.lines.DriverBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove Drivers Controller.
 * <p>This controller removes one record in Drivers table.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/RemoveDriversController")
public class RemoveDriversController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            DAO<DriverBean> driversDAO = new DriversDAO();
            driversDAO.remove(new DriverBean(id));

            request.getRequestDispatcher("DriversController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}