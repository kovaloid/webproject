package com.epam.project.controllers.cars;

import com.epam.project.beans.Table;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import com.epam.project.util.CarDefiner;
import com.epam.project.util.ColorDefiner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CarsController")
public class CarsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO carsDAO = new CarsDAO();
        Table carsTable = carsDAO.getAll();
        request.getSession().setAttribute("cars_table", carsTable);

        DAO driversDAO = new DriversDAO();
        Table driversTable = driversDAO.getAll();
        request.getSession().setAttribute("drivers_list", driversTable);

        List<String> colorsList = ColorDefiner.getColorsList();
        request.getSession().setAttribute("colors_list", colorsList);

        List<String> carsList = CarDefiner.getCarsList();
        request.getSession().setAttribute("cars_list", carsList);

        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/cars.jsp").forward(request, response);
    }
}