package com.epam.project.controllers.cars.data_controls;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove Cars Controller.
 * <p>This controller removes one record in Cars table.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/RemoveCarsController")
public class RemoveCarsController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            DAO<CarBean> carsDAO = new CarsDAO();
            carsDAO.remove(new CarBean(id));

            request.getRequestDispatcher("CarsController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}