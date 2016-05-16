package com.epam.project.controllers.cars;

import com.epam.project.beans.Table;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import com.epam.project.utils.CarDefiner;
import com.epam.project.utils.ColorDefiner;
import com.epam.project.utils.TableSeparator;

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

        Table[] carsTablesArray = TableSeparator.separate(carsTable);
        int carsTablesAmount = carsTablesArray.length;
        int carsTableNumber = TableSeparator.getTableNumber(request.getParameter("number"), carsTablesAmount);

        request.getSession().setAttribute("cars_table_number", carsTableNumber);
        request.getSession().setAttribute("cars_tables_amount", carsTablesAmount);
        request.getSession().setAttribute("cars_table", carsTablesArray[carsTableNumber-1]);

        DAO driversDAO = new DriversDAO();
        Table driversTable = driversDAO.getAll();
        request.getSession().setAttribute("drivers_list", driversTable);

        List<String> colorsList = ColorDefiner.getColorsList();
        request.getSession().setAttribute("colors_list", colorsList);

        List<String> carsList = CarDefiner.getCarsList();
        request.getSession().setAttribute("cars_list", carsList);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/cars.jsp").forward(request, response);
    }
}