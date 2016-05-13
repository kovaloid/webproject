package com.epam.project.controllers.cars.data_controls;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateCarsController")
public class UpdateCarsController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DAO<CarBean> dao = new CarsDAO();

            Integer id = Integer.valueOf(request.getParameter("id"));
            String number = request.getParameter("car_number");
            String brand = request.getParameter("brand");
            String color = request.getParameter("color");
            String ready = request.getParameter("ready");
            Integer driverId = Integer.valueOf(request.getParameter("driver_id"));

            dao.update(new CarBean(id, number, brand, color, driverId, ready));
            request.getRequestDispatcher("CarsController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}