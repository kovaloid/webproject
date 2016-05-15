package com.epam.project.controllers.cars;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.lines.DriverBean;
import com.epam.project.beans.TableBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import com.epam.project.service.Definer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CarsController")
public class CarsController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO<CarBean> cardao = new CarsDAO();
        TableBean cars = cardao.getAll();
        request.getSession().setAttribute("cars_table", cars);

        DAO<DriverBean> driverdao = new DriversDAO();
        TableBean drivers = driverdao.getAll();
        request.getSession().setAttribute("drivers_list", drivers);



        //----------------------

        List<String> colorsList = Definer.getColorsList();
        request.getSession().setAttribute("colors_list", colorsList);

        List<String> carsList = Definer.getCarsList();
        request.getSession().setAttribute("cars_list", carsList);

        //----------------------

        request.getRequestDispatcher("/WEB-INF/jsp/data_tables/cars.jsp").forward(request, response);
    }

}