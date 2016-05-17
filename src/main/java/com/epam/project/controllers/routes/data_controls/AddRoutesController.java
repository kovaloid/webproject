package com.epam.project.controllers.routes.data_controls;

import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
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
 * Add Routes Controller.
 * <p>This controller adds new record in Routes table.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/AddRoutesController")
public class AddRoutesController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String locale = (String) request.getSession().getAttribute("locale");
            if (locale == null) locale = "en";
            ResourceBundle bundle = ResourceBundle.getBundle("locale", new Locale(locale));

            String routeName = request.getParameter("route_name");
            String lengthParam = request.getParameter("length");
            String priceParam = request.getParameter("price");

            if (lengthParam.equals("")) lengthParam = "0";
            if (lengthParam.length() > 8)
                throw new NumberFormatException("[length] " + bundle.getString("local.data.exception.large_value"));

            if (priceParam.equals("")) priceParam = "0";
            if (priceParam.length() > 8)
                throw new NumberFormatException("[price] " + bundle.getString("local.data.exception.large_value"));

            Integer length;
            Integer price;

            try {
                length = Integer.valueOf(lengthParam);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("[length] " + bundle.getString("local.data.exception.string_value"));
            }

            try {
                price = Integer.valueOf(priceParam);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("[price] " + bundle.getString("local.data.exception.string_value"));
            }

            if (length < 0)
                throw new NumberFormatException("[length] " + bundle.getString("local.data.exception.negative_value"));
            if (price < 0)
                throw new NumberFormatException("[price] " + bundle.getString("local.data.exception.negative_value"));

            DAO<RouteBean> routesDAO = new RoutesDAO();
            routesDAO.add(new RouteBean(routeName, length, price));

            request.getRequestDispatcher("RoutesController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}