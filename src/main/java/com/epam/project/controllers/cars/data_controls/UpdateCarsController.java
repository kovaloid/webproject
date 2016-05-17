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
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Update Cars Controller.
 * <p>This controller updates one record in Cars table.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/UpdateCarsController")
public class UpdateCarsController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String locale = (String) request.getSession().getAttribute("locale");
            if (locale == null) locale = "en";
            ResourceBundle bundle = ResourceBundle.getBundle("locale", new Locale(locale));

            String idParam = request.getParameter("id");

            if (idParam.equals("")) idParam = "0";
            if (idParam.length() > 8)
                throw new NumberFormatException("[id] " + bundle.getString("local.data.exception.large_value"));

            Integer id;

            try {
                id = Integer.valueOf(idParam);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("[id] " + bundle.getString("local.data.exception.string_value"));
            }

            if (id < 0)
                throw new NumberFormatException("[id] " + bundle.getString("local.data.exception.negative_value"));

            String number = request.getParameter("car_number");
            String brand = request.getParameter("brand");
            String color = request.getParameter("color");
            String ready = request.getParameter("ready");
            Integer driverId = Integer.valueOf(request.getParameter("driver_id"));

            DAO<CarBean> dao = new CarsDAO();
            dao.update(new CarBean(id, number, brand, color, driverId, ready));

            request.getRequestDispatcher("CarsController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}