package com.epam.project.controllers.drivers;

import com.epam.project.beans.Table;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriversController")
public class DriversController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO driversDAO = new DriversDAO();
        Table driversTable = driversDAO.getAll();
        request.getSession().setAttribute("drivers_table", driversTable);

        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/drivers.jsp").forward(request, response);
    }
}