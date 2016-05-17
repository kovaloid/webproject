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
import java.util.Locale;
import java.util.ResourceBundle;

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