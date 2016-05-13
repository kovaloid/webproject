package com.epam.project.controllers.drivers.data_controls;

import com.epam.project.beans.lines.DriverBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.DriversDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateDriversController")
public class UpdateDriversController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DAO<DriverBean> dao = new DriversDAO();
            Integer id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String gender = request.getParameter("gender");
            Integer phone = Integer.valueOf(request.getParameter("phone"));
            dao.update(new DriverBean(id, name, surname, gender, phone));
            request.getRequestDispatcher("DriversController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }
    }
}