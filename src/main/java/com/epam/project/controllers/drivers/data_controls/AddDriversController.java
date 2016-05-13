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

@WebServlet("/AddDriversController")
public class AddDriversController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO<DriverBean> dao = new DriversDAO();
        String name = request.getParameter("first_name");
        String surname = request.getParameter("last_name");
        dao.add(new DriverBean(name, surname));
        request.getRequestDispatcher("DriversController").forward(request, response);
    }
}